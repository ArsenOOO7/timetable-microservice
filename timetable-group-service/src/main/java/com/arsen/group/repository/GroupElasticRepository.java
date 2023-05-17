package com.arsen.group.repository;

import com.arsen.group.domain.GroupDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupElasticRepository extends ElasticsearchRepository<GroupDocument, Long> {

    List<GroupDocument> findAllByGroupNameLike(String query);

}
