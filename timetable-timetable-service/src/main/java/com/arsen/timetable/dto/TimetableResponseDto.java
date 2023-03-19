package com.arsen.timetable.dto;

import com.arsen.timetable.domain.LessonType;
import com.arsen.timetable.dto.group.GroupDto;
import com.arsen.timetable.dto.readonly.ClassroomDto;
import com.arsen.timetable.dto.readonly.SubjectDto;
import com.arsen.timetable.dto.readonly.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class TimetableResponseDto {

    private long id;

    private SubjectDto subject;

    private TeacherDto teacher;

    private ClassroomDto classroom;


    private short lessonNumber;

    private LocalDate lessonDate;

    private LessonType lessonType;

    private boolean online;

    private Set<GroupDto> groups;

    public TimetableResponseDto(long id, SubjectDto subjectDto, TeacherDto teacherDto, ClassroomDto classroomDto, short lessonNumber, LocalDate lessonDate, LessonType lessonType, boolean online) {
        this.id = id;
        this.subject = subjectDto;
        this.teacher = teacherDto;
        this.classroom = classroomDto;
        this.lessonNumber = lessonNumber;
        this.lessonDate = lessonDate;
        this.lessonType = lessonType;
        this.online = online;
    }
}
