package com.pnu.system.elasticsearch.service;


import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.exception.InvalidParameterException;
import com.pnu.system.common.utils.TimetableCollectionUtils;
import com.pnu.system.elasticsearch.domain.BaseDocument;
import com.pnu.system.elasticsearch.repository.ExtendedElasticsearchRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class AbstractElasticsearchService<T extends BaseDocument> {

    @Deprecated
    public T getOne(String id) {
        if (StringUtils.isBlank(id)) {
            throw new EntityNotFoundException(null, getEntityType().getSimpleName());
        }
        return getOne(UUID.fromString(id));
    }

    public T getOne(UUID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString(), getEntityType().getSimpleName()));
    }

    @Deprecated
    public List<T> getAllByStringIds(Collection<String> ids) {
        return getAll(TimetableCollectionUtils.stringIdsToUuids(ids));
    }

    public List<T> getAll(Collection<UUID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<T> documents = (List<T>) getRepository().findAllById(ids);
        List<UUID> actualIds = documents.stream().map(BaseDocument::getId).toList();
        Collection<UUID> notFoundIds = CollectionUtils.removeAll(ids, actualIds);
        if (CollectionUtils.isNotEmpty(notFoundIds)) {
            throw new InvalidParameterException("Not found by ids: %s".formatted(StringUtils.join(",", notFoundIds)));
        }
        return documents;
    }

    public T save(T document) {
        return document.isNew() ? create(document) : update(document);
    }

    public T create(T document) {
        if (!document.isNew()) {
            throw new InvalidParameterException("Invalid document with in create operation.");
        }

        document.setId(UUID.randomUUID());
        return create(List.of(document)).getFirst();
    }

    public T update(T document) {
        return update(List.of(document)).getFirst();
    }

    public List<T> create(List<T> documents) {
        //TODO 2/6/25: Implement mass create (maybe add more logic when needed)
        for (T document : documents) {
            if (document.isNew()) {
                document.setId(UUID.randomUUID());
            }
        }
        return getRepository().saveAll(documents);
    }

    public List<T> update(List<T> documents) {
        //TODO 2/6/25: Implement mass update (maybe add more logic when needed)
        return getRepository().saveAll(documents);
    }

    protected abstract Class<T> getEntityType();

    protected abstract ExtendedElasticsearchRepository<T, UUID> getRepository();

}
