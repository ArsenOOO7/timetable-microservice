package com.pnu.system.lessonlocation.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonLocationTypeUpdateDto {
    @NotBlank
    private String id;
    @NotBlank
    @Max(255)
    private String name;
    @NotBlank
    @Max(32)
    private String shortName;
    @NotNull
    private Integer version;
}
