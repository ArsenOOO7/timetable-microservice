package com.arsen.timetable.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LessonCreatedBrieflyResponseDto {

    private long id;
    private LocalDate lessonDate;

}
