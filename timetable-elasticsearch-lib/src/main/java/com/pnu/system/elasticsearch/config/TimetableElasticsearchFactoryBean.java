package com.pnu.system.elasticsearch.config;

import com.pnu.system.elasticsearch.repository.ExtendedElasticsearchRepositoryImpl;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactory;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@see <a href = "https://docs.spring.io/spring-data/commons/docs/1.9.0.RELEASE/reference/html/#repositories.single-repository-behaviour">Docs</a>}
 */
//TODO 3/5/25: TRY WITH SEGMENTS
public class TimetableElasticsearchFactoryBean<R extends ElasticsearchRepository<T, I>, T, I extends Serializable> extends ElasticsearchRepositoryFactoryBean<R, T, I> {

    @Nullable
    private ElasticsearchOperations operations;

    /**
     * Creates a new {@link ElasticsearchRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public TimetableElasticsearchFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    public void setElasticsearchOperations(ElasticsearchOperations operations) {
        super.setElasticsearchOperations(operations);
        this.operations = operations;
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        Objects.requireNonNull(operations);
        return new TimetableElasticsearchFactory<>(operations);
    }

    private static class TimetableElasticsearchFactory<T, I extends Serializable> extends ElasticsearchRepositoryFactory {

        private final ElasticsearchOperations elasticsearchOperations;

        public TimetableElasticsearchFactory(ElasticsearchOperations elasticsearchOperations) {
            super(elasticsearchOperations);
            this.elasticsearchOperations = elasticsearchOperations;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation metadata) {
            ElasticsearchEntityInformation<T, I> entityInformation =
                    getEntityInformation((Class<T>) metadata.getDomainType());
            return new ExtendedElasticsearchRepositoryImpl<>(entityInformation, elasticsearchOperations);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return ExtendedElasticsearchRepositoryImpl.class;
        }
    }
}
