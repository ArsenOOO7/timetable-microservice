package com.arsen.classroom.repository;

import com.arsen.classroom.domain.Classroom;
import com.arsen.classroom.dto.ClassroomResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("select new com.arsen.classroom.dto.ClassroomResponseDto(cl.id, cl.name, cl.address) from Classroom cl where cl.id = :id")
    Optional<ClassroomResponseDto> findDtoById(@Param("id") long id);

    @Query("select new com.arsen.classroom.dto.ClassroomResponseDto(cl.id, cl.name, cl.address) from Classroom cl where lower(cl.name) like lower(concat('%', :name, '%'))")
    List<ClassroomResponseDto> findAllByName(@Param("name") String name);

}
