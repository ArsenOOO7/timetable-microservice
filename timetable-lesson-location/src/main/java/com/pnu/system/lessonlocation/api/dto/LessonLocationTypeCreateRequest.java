package com.pnu.system.lessonlocation.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonLocationTypeCreateRequest {

    @NotBlank
    @Size(max = 255)
    private String name;
    @NotBlank
    @Size(max = 32)
    private String shortName;

}
