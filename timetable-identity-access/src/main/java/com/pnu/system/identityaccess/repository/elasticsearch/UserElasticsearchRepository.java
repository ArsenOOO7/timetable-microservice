package com.pnu.system.identityaccess.repository.elasticsearch;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.pnu.system.elasticsearch.repository.ExtendedElasticsearchRepository;
import com.pnu.system.identityaccess.domain.document.UserDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserElasticsearchRepository extends ExtendedElasticsearchRepository<UserDocument, UUID> {

    default List<UserDocument> getList(BoolQuery filter) {
        NativeQuery query = new NativeQueryBuilder()
                .withFilter(filter._toQuery())
                .withPageable(Pageable.unpaged())
                .build();
        return findAll(query);
    }
}
