package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdateRequest extends VersionRequest {

    @NotBlank
    @Size(max = 128)
    private String title;
    private String description;

}
