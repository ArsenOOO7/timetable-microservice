package com.arsen.timetable.repository;

import com.arsen.timetable.domain.readonly.ClassroomRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClassroomReadRepository extends JpaRepository<ClassroomRead, Long> {

    @Query("select count(lesson) > 0 from Lesson lesson where lesson.classroom.id = :classroom and lesson.lessonDate = :date and lesson.lessonNumber = :lessonNumber")
    boolean isClassroomBusy(@Param("classroom") long classroomId, @Param("date") LocalDate date, @Param("lessonNumber") short lessonNumber);

}
