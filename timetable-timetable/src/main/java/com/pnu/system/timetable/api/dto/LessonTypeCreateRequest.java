package com.pnu.system.timetable.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonTypeCreateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String shortName;

}
