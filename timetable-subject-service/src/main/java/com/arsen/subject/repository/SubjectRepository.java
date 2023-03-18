package com.arsen.subject.repository;

import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {


    @Query("select new com.arsen.subject.dto.SubjectDto(s.id, s.subjectName) from Subject s where s.id = :id")
    Optional<SubjectDto> findDtoById(@Param("id") long id);

}
