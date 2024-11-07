package com.pnu.system.timetable.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonTypeUpdateRequest {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String shortName;
    @NotNull
    private Integer version;

}
