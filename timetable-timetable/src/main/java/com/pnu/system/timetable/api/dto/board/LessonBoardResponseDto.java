package com.pnu.system.timetable.api.dto.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LessonBoardResponseDto {

    private LocalDate date;
    private List<LessonBoardLessonDto> lessons;

}
