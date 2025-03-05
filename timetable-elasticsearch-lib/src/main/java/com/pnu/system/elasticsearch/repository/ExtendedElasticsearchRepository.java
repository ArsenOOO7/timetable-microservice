package com.pnu.system.elasticsearch.repository;

import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ExtendedElasticsearchRepository<T, I extends Serializable> extends ElasticsearchRepository<T, I> {

    List<T> saveAll(List<T> documents);

    List<T> findAll(Query query);

    T getOne(Query query);

}
