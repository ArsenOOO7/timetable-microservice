package com.pnu.system.timetable.api.dto.search;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BaseLessonSearchRequest {

    @NotNull
    private LocalDate fromDate;
    @NotNull
    private LocalDate toDate;

}
