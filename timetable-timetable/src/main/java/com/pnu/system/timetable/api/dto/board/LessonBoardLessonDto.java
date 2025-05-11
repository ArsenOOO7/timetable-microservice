package com.pnu.system.timetable.api.dto.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LessonBoardLessonDto {

    private String id;

    private LocalDate date;
    private short number;

    private LessonBoardTypeDto type;
    private LessonBoardLocationDto location;
    private LessonBoardSubjectDto subject;
    private LessonBoardUserDto teacher;
    private List<LessonBoardGroupDto> groups;

    private boolean online;

}
