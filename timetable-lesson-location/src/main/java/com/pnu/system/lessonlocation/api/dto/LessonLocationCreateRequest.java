package com.pnu.system.lessonlocation.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonLocationCreateRequest {
    @NotBlank
    private String locationTypeId;
    @NotBlank
    @Max(32)
    private String name;
    @NotBlank
    @Max(255)
    private String address;
}
