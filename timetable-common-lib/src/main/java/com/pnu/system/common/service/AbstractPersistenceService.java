package com.pnu.system.common.service;

import com.pnu.system.common.domain.BaseEntityProvider;
import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.exception.InvalidParameterException;
import com.pnu.system.common.utils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Transactional
public abstract class AbstractPersistenceService<T extends BaseEntityProvider> {

    public T getOne(String id) {
        if (id == null) {
            throw new EntityNotFoundException(null, getEntityType().getSimpleName());
        }
        return getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, getEntityType().getSimpleName()));
    }

    public List<T> getAll(Collection<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<T> entities = getRepository().findAllById(ids);
        List<String> actualIds = BeanUtils.getIds(entities);
        Collection<String> notFoundIds = CollectionUtils.removeAll(ids, actualIds);
        if (CollectionUtils.isNotEmpty(notFoundIds)) {
            throw new InvalidParameterException("Not found by ids: %s".formatted(String.join(",", notFoundIds)));
        }
        return entities;
    }

    public T getCloned(String id) {
        return getCloned(getOne(id));
    }

    public T getCloned(T entity) {
        initEntity(entity);
        return ObjectUtils.clone(entity);
    }

    @Transactional
    public T save(T entity) {
        return entity.isNew() ? create(entity) : update(entity);
    }

    @Transactional
    public T create(T entity) {
        return create(List.of(entity)).getFirst();
    }

    @Transactional
    public T update(T entity) {
        return update(List.of(entity)).getFirst();
    }

    @Transactional
    public List<T> create(Collection<T> entities) {
        return internalCreate(entities);
    }

    @Transactional
    public List<T> update(Collection<T> entities) {
        return internalUpdate(entities);
    }

    private List<T> internalCreate(Collection<T> entities) {
        //TODO: the purpose is to do some logic here before saving
        return getRepository().saveAll(entities);
    }

    private List<T> internalUpdate(Collection<T> entities) {
        //TODO: the purpose is to do some logic here before updating
        return getRepository().saveAll(entities);
    }

    @Transactional
    public void delete(String id) {
        delete(getOne(id));
    }

    @Transactional
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    protected void initEntity(T entity) {

    }

    protected abstract Class<T> getEntityType();

    protected abstract JpaRepository<T, String> getRepository();

}
