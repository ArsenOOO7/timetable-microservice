package com.pnu.system.laboratorycontrol.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursePrimaryLinkCreateRequest {

    @NotBlank
    private String courseId;
    @NotBlank
    private String link;
    @NotBlank
    private String label;

}
