package com.arsen.timetable.repository;

import com.arsen.timetable.domain.readonly.TeacherRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TeacherReadRepository extends JpaRepository<TeacherRead, Long> {

    @Query("select count(lesson) > 0 from Lesson lesson where lesson.teacher.id = :teacher and lesson.lessonDate = :date and lesson.lessonNumber = :lessonNumber")
    boolean isTeacherBusy(@Param("teacher") long teacher, @Param("date") LocalDate date, @Param("lessonNumber") short lessonNumber);

}
