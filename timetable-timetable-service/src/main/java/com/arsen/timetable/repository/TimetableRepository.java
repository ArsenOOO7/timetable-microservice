package com.arsen.timetable.repository;

import com.arsen.timetable.domain.Lesson;
import com.arsen.timetable.dto.TimetableResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TimetableRepository extends JpaRepository<Lesson, Long> {

    @Query("select " +
            "new com.arsen.timetable.dto.TimetableResponseDto(" +
            "lesson.id, " +
            "new com.arsen.timetable.dto.readonly.SubjectDto(lesson.subject.id, lesson.subject.subjectName)," +
            "new com.arsen.timetable.dto.readonly.TeacherDto(lesson.teacher.id, lesson.teacher.firstName, lesson.teacher.lastName, lesson.teacher.fatherName, lesson.teacher.meetingLink)," +
            "new com.arsen.timetable.dto.readonly.ClassroomDto(lesson.classroom.id, lesson.classroom.name, lesson.classroom.address)," +
            "lesson.lessonNumber," +
            "lesson.lessonDate," +
            "lesson.lessonType," +
            "lesson.online" +
            ") " +
            "from Lesson lesson where lesson.id in :ids order by lesson.id")
    List<TimetableResponseDto> findTimetableByGroup(@Param("ids") Set<Long> ids);

    @Query("select " +
            "new com.arsen.timetable.dto.TimetableResponseDto(" +
            "lesson.id, " +
            "new com.arsen.timetable.dto.readonly.SubjectDto(lesson.subject.id, lesson.subject.subjectName)," +
            "new com.arsen.timetable.dto.readonly.TeacherDto(lesson.teacher.id, lesson.teacher.firstName, lesson.teacher.lastName, lesson.teacher.fatherName, lesson.teacher.meetingLink)," +
            "new com.arsen.timetable.dto.readonly.ClassroomDto(lesson.classroom.id, lesson.classroom.name, lesson.classroom.address)," +
            "lesson.lessonNumber," +
            "lesson.lessonDate," +
            "lesson.lessonType," +
            "lesson.online" +
            ") " +
            "from Lesson lesson where lesson.lessonDate between :start and :end and lesson.teacher.id = :teacher order by lesson.id")
    List<TimetableResponseDto> findTimetableByTeacher(@Param("teacher") long teacher, @Param("start") LocalDate start, @Param("end") LocalDate end);






}
