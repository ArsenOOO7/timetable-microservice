package com.arsen.timetable.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LessonSearchDto {

    private Long groupId;
    private Long teacherId;

    private LocalDate startDate;
    private LocalDate endDate;

}
