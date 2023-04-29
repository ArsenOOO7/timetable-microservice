package com.arsen.timetable.dto;

import com.arsen.timetable.domain.LessonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LessonDto {

    private long subjectId;
    private long teacherId;
    private long classroomId;


    private short lessonNumber;
    private LocalDate lessonDate;

    private LessonType lessonType;

    private boolean online = false;

    private Set<Long> groupIds;

}
