package com.arsen.subject.repository;

import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {


    @Query("select new com.arsen.subject.dto.SubjectResponseDto(s.id, s.subjectName) from Subject s where s.id = :id")
    Optional<SubjectResponseDto> findDtoById(@Param("id") long id);

    @Query("select new com.arsen.subject.dto.SubjectResponseDto(s.id, s.subjectName) from Subject s where lower(s.subjectName) like lower(concat('%', :query, '%'))")
    List<SubjectResponseDto> findAllByQuery(@Param("query") String query);

}
