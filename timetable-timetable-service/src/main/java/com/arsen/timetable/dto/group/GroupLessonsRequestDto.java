package com.arsen.timetable.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class GroupLessonsRequestDto {

    private long groupId;
    private LocalDate startDate;
    private LocalDate endDate;

}
