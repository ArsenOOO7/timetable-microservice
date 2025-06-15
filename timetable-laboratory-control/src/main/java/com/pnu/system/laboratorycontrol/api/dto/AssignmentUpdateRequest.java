package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionRequest;
import com.pnu.system.laboratorycontrol.constant.AssignmentVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AssignmentUpdateRequest extends VersionRequest {

    @NotBlank
    private String title;
    private String description;

    private AssignmentVisibility visibility;
    private ZonedDateTime appearAt;

    private Integer maxGrade;
    private ZonedDateTime deadline;

}
