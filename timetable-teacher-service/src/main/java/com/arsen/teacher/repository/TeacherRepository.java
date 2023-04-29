package com.arsen.teacher.repository;

import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherResponseDto;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select new com.arsen.teacher.dto.TeacherResultSearchDto(t.id, t.firstName, t.lastName, t.fatherName) " +
            "from Teacher t where lower(t.firstName) like lower(concat('%', :query, '%')) " +
            "or lower(t.lastName) like lower(concat('%', :query, '%')) " +
            "or lower(t.fatherName) like lower(concat('%', :query, '%')) " +
            "or lower(concat(t.firstName, ' ', t.lastName, ' ', t.fatherName)) like lower(concat('%', :query, '%'))")
    List<TeacherResultSearchDto> findAllByQuery(@Param("query") String query);

    @Query("select new com.arsen.teacher.dto.TeacherResponseDto(t.id, t.firstName, t.lastName, t.fatherName, t.email, t.meetingLink) from Teacher t where t.id = :id")
    Optional<TeacherResponseDto> findDtoById(@Param("id") long id);

}
