package com.pnu.system.timetable.api.dto.board;

import com.pnu.system.timetable.domain.TimetableGroupSnapshot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LessonBoardLessonDto {

    private String id;

    private LocalDate date;
    private short number;

    private LessonBoardTypeDto type;
    private LessonBoardLocationDto location;
    private LessonBoardSubjectDto subject;
    private LessonBoardUserDto teacher;
    private List<TimetableGroupSnapshot> groups;

    private boolean online;

}
