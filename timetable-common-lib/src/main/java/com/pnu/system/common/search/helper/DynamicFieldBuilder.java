package com.pnu.system.common.search.helper;

import com.pnu.system.common.domain.BaseEntity;
import com.pnu.system.common.domain.QAuditableEntity;
import com.pnu.system.common.domain.QBaseEntity;
import com.pnu.system.common.domain.QVersionEntity;
import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.CollectionPathBase;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringPath;
import lombok.SneakyThrows;
import org.apache.commons.collections4.SetUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.pnu.system.common.constant.CommonFieldName.ID;

public class DynamicFieldBuilder<T extends BaseEntity> {

    private static final String COLLECTION_QUERY_TYPE_NAME = "queryType";
    private static final Set<String> IGNORE_FIELDS = Set.of("_super");

    private static final Set<Class<?>> PRIMITIVE_TYPES = SetUtils.hashSet(StringPath.class, DateTimePath.class, EnumPath.class, NumberPath.class);
    private static final Set<Class<?>> INHERITANCE_TYPES = SetUtils.hashSet(QAuditableEntity.class, QVersionEntity.class, QBaseEntity.class);
    private static final Set<Class<?>> COLLECTION_TYPES = SetUtils.hashSet(ListPath.class, SetPath.class);

    private static final int MAX_DEEP = 5;

    private final int deep;
    private final EntityPathBase<T> basePath;
    private final Map<String, Path<?>> fields = new HashMap<>();
    private final List<EntityPath<?>> references = new ArrayList<>();
    private final Map<CollectionExpression<?, BaseEntity>, Path<BaseEntity>> collectionJoins = new HashMap<>();

    public DynamicFieldBuilder(EntityPathBase<T> basePath, int deep) {
        this.deep = deep;
        this.basePath = basePath;
        scanFields();
    }

    public DynamicFieldBuilder(EntityPathBase<T> basePath) {
        this(basePath, 0);
    }

    private void scanFields() {
        Field[] fields = basePath.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())
                    || IGNORE_FIELDS.contains(field.getName())) {
                continue;
            }

            if (INHERITANCE_TYPES.contains(field.getType())) {
                continue;
            }

            if (PRIMITIVE_TYPES.contains(field.getType())) {
                addField(field);
                continue;
            }

            if (COLLECTION_TYPES.contains(field.getType())) {
                addCollection(field);
                continue;
            }

            addReference(field);
        }
    }

    @SneakyThrows
    private void addField(Field field) {
        Path<?> path = (Path<?>) field.get(basePath);
        fields.put(field.getName(), path);
    }

    @SneakyThrows
    private void addReference(Field field) {
        if (deep > MAX_DEEP) {
            return;
        }
        EntityPathBase<? extends BaseEntity> path = (EntityPathBase<? extends BaseEntity>) field.get(basePath);
        if (Objects.isNull(path)) {
            return;
        }

        references.add(path);
        DynamicFieldBuilder<?> builder = new DynamicFieldBuilder<>(path, deep + 1);
        builder.getFields().forEach((fieldName, value) -> fields.put(String.join(".", field.getName(), fieldName), value));
    }

    @SneakyThrows
    private void addCollection(Field field) {
        if (deep > MAX_DEEP) {
            return;
        }

        Path<?> fieldValue = (Path<?>) field.get(basePath);
        CollectionPathBase<?, ?, ?> collectionPathBase = (CollectionPathBase<?, ?, ?>) fieldValue;
        SimpleExpression<?> any = collectionPathBase.any();

        if (any instanceof EntityPathBase<?>) {
            Field queryTypeField = fieldValue.getClass().getDeclaredField(COLLECTION_QUERY_TYPE_NAME);
            queryTypeField.setAccessible(true);
            Class<?> queryTypeValue = (Class<?>) queryTypeField.get(fieldValue);
            EntityPathBase<BaseEntity> path = (EntityPathBase<BaseEntity>) queryTypeValue.getConstructor(String.class).newInstance(field.getName());

            collectionJoins.put((CollectionExpression<?, BaseEntity>) collectionPathBase, path);
            fields.put(field.getName(), path);

            DynamicFieldBuilder<?> builder = new DynamicFieldBuilder<>(path, deep + 1);
            builder.getFields().forEach((fieldName, value) -> fields.put(String.join(".", field.getName(), fieldName), value));
        }
    }

    public StringPath getIdField() {
        return (StringPath) fields.get(ID);
    }

    public Path<?> getField(String fieldName) {
        return fields.get(fieldName);
    }

    public Map<CollectionExpression<?, BaseEntity>, Path<BaseEntity>> getCollectionJoins() {
        return collectionJoins;
    }

    public List<EntityPath<?>> getReferences() {
        return references;
    }

    protected Map<String, Path<?>> getFields() {
        return fields;
    }
}
