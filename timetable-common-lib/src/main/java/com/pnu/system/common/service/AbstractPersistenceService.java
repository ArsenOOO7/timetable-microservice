package com.pnu.system.common.service;

import com.pnu.system.common.domain.BaseEntityProvider;
import com.pnu.system.common.exception.EntityNotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
public abstract class AbstractPersistenceService<T extends BaseEntityProvider> {

    public T getOne(String guid) {
        T entity = getRepository().findById(guid)
                .orElseThrow(() -> new EntityNotFoundException(guid, getEntityType().getSimpleName()));
        initEntity(entity);
        return entity;
    }

    public T getCloned(String guid) {
        return getCloned(getOne(guid));
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
    public void delete(String guid) {
        delete(getOne(guid));
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
