package com.arsen.teacher.repository;

import com.arsen.teacher.domain.TeacherDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherElasticRepository extends ElasticsearchRepository<TeacherDocument, Long> {

    List<TeacherDocument> findAllByFullNameLike(String fullName);

}
