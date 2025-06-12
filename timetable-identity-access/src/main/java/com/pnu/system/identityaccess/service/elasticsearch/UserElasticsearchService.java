package com.pnu.system.identityaccess.service.elasticsearch;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.dto.UserDto;
import com.pnu.system.elasticsearch.repository.ExtendedElasticsearchRepository;
import com.pnu.system.elasticsearch.service.AbstractElasticsearchService;
import com.pnu.system.identityaccess.domain.document.UserDocument;
import com.pnu.system.identityaccess.mapper.UserMapper;
import com.pnu.system.identityaccess.repository.elasticsearch.UserElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.queryparser.flexible.standard.QueryParserUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserElasticsearchService extends AbstractElasticsearchService<UserDocument> {

    private final UserMapper mapper;
    private final UserElasticsearchRepository repository;

    public List<UserDto> getListByFilter(String filter, UserType type) {
        BoolQuery.Builder builder = QueryBuilders.bool();
        builder.must(QueryBuilders.queryString()
                .query("*" + QueryParserUtil.escape(filter) + "*")
                .fields("firstName", "lastName")
                .defaultOperator(Operator.And).build()._toQuery());
        builder.must(QueryBuilders.queryString()
                .query(QueryParserUtil.escape(type.name()))
                .fields("type")
                .defaultOperator(Operator.And)
                .build()._toQuery());

        List<UserDocument> users = repository.getList(builder.build());
        return mapper.asUserDtos(users);
    }

    @Override
    protected Class<UserDocument> getEntityType() {
        return UserDocument.class;
    }

    @Override
    protected ExtendedElasticsearchRepository<UserDocument, UUID> getRepository() {
        return repository;
    }
}
