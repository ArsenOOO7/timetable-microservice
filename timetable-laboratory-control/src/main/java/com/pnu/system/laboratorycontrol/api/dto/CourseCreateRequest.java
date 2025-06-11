package com.pnu.system.laboratorycontrol.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateRequest {

    @NotBlank
    @Size(max = 128)
    private String title;
    @NotBlank
    @Size(max = 40)
    private String subjectId;

}
