package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursePrimaryLinkUpdateRequest extends VersionRequest {

    @NotBlank
    private String link;
    @NotBlank
    private String label;

}
