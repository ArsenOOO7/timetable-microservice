package com.pnu.system.elasticsearch.util;

import com.pnu.system.common.domain.BaseEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.hibernate.search.mapper.orm.work.SearchIndexingPlan;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ElasticSynchronizationUtil {

    private final EntityManager entityManager;

    @SneakyThrows
    public void synchronize(int threadNumbers, Class<?> type) {
        SearchSession session = Search.session(entityManager);
        MassIndexer massIndexer = session.massIndexer(type)
                .threadsToLoadObjects(threadNumbers);
        massIndexer.startAndWait();
    }

    public <T extends BaseEntity> void synchronize(List<T> entities) {
        SearchSession session = Search.session(entityManager);
        SearchIndexingPlan searchIndexingPlan = session.indexingPlan();
        entities.forEach(searchIndexingPlan::addOrUpdate);
    }
}
