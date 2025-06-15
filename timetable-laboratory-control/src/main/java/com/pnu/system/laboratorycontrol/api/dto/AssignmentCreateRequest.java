package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.laboratorycontrol.constant.AssignmentType;
import com.pnu.system.laboratorycontrol.constant.AssignmentVisibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AssignmentCreateRequest {

    @NotBlank
    private String courseId;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private AssignmentType type;
    private AssignmentVisibility visibility;
    private ZonedDateTime appearAt;

    private Integer maxGrade;
    private ZonedDateTime deadline;

}
