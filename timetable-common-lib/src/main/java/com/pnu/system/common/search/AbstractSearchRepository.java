package com.pnu.system.common.search;

import com.pnu.system.common.domain.BaseEntity;
import com.pnu.system.common.exception.InvalidParameterException;
import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.common.search.dto.SearchField;
import com.pnu.system.common.search.helper.DynamicFieldBuilder;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.annotation.PostConstruct;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.pnu.system.common.constant.CommonFieldName.ID;

@Transactional(readOnly = true)
public abstract class AbstractSearchRepository<T extends BaseEntity> {

    private static final Set<String> KEEP_ALWAYS = Set.of(ID);

    private DynamicFieldBuilder<T> dynamicFieldBuilder;

    @PostConstruct
    public void init() {
        this.dynamicFieldBuilder = new DynamicFieldBuilder<>(getPath());
    }

    public List<Map<String, Object>> search(ReportSearchRequest request) {
        List<SearchField> fields = request.getFields();
        JPAQuery<Tuple> query = QueryDslFactory.getQueryFactory()
                .select(getJpaFields(fields));

        query.from(getPath());
        List<Map<String, Object>> result = transform(fields, query.fetch());
        getCollectionValues(fields, result);
        return result;
    }

    private void getCollectionValues(List<SearchField> fields, List<Map<String, Object>> result) {
        List<SearchField> collectionFields = fields.stream().filter(SearchField::isCollection).toList();
        if (CollectionUtils.isEmpty(collectionFields)) {
            return;
        }

        Map<String, Map<String, Object>> groupedById = result.stream().collect(Collectors.toMap(map -> map.get(ID).toString(), Function.identity()));
        JPAQuery<Tuple> query = QueryDslFactory.getQueryFactory()
                .select(getJpaFields(fields, true));
        query.from(getPath());
        query.where(dynamicFieldBuilder.getIdField().in(groupedById.keySet()));
        dynamicFieldBuilder.getCollectionJoins().forEach(query::leftJoin);

        Map<String, Map<String, List<Object>>> collectionValuesMap = new HashMap<>();
        List<Tuple> tuples = query.fetch();
        tuples.forEach(tuple -> {
            String id = tuple.get(dynamicFieldBuilder.getIdField()).toString();
            Map<String, List<Object>> row = collectionValuesMap.computeIfAbsent(id, k -> new HashMap<>());
            collectionFields.forEach(field ->
                    row.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add(tuple.get(dynamicFieldBuilder.getField(field.getName()))));
        });

        groupedById.forEach((id, values) -> values.putAll(collectionValuesMap.get(id)));
    }

    private List<Map<String, Object>> transform(List<SearchField> fields, List<Tuple> results) {
        return results.stream()
                .map(result -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    fields.forEach(field -> map.put(field.getName(), result.get(dynamicFieldBuilder.getField(field.getName()))));
                    return map;
                }).toList();
    }

    private Path<?>[] getJpaFields(List<SearchField> fields) {
        return getJpaFields(fields, false);
    }

    private Path<?>[] getJpaFields(List<SearchField> fields, boolean collectionOnly) {
        List<Path<?>> pathList = new ArrayList<>();
        for (SearchField field : fields) {
            if (!KEEP_ALWAYS.contains(field.getName())
                    && (!field.isCollection() && collectionOnly || field.isCollection() && !collectionOnly)) {
                continue;
            }
            Path<?> fieldPath = dynamicFieldBuilder.getField(field.getName());
            if (Objects.isNull(fieldPath)) {
                throw new InvalidParameterException("Invalid path: " + field.getName());
            }
            pathList.add(fieldPath);
        }
        return pathList.toArray(Path<?>[]::new);
    }

    protected abstract EntityPathBase<T> getPath();

}
