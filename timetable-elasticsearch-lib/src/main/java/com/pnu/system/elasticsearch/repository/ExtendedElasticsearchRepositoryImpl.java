package com.pnu.system.elasticsearch.repository;

import com.pnu.system.common.constant.CommonConstants;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExtendedElasticsearchRepositoryImpl<T, I extends Serializable> extends SimpleElasticsearchRepository<T, I> implements ExtendedElasticsearchRepository<T, I> {

    public ExtendedElasticsearchRepositoryImpl(ElasticsearchEntityInformation<T, I> metadata, ElasticsearchOperations operations) {
        super(metadata, operations);
    }

    @Override
    public List<T> saveAll(List<T> documents) {
        return ListUtils.partition(documents, CommonConstants.BATCH_SIZE)
                .stream()
                .map(this::save)
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public List<T> findAll(Query query) {
        return operations.search(query, entityClass).stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public T getOne(Query query) {
        return Optional.ofNullable(operations.searchOne(query, entityClass)).map(SearchHit::getContent).orElse(null);
    }
}
