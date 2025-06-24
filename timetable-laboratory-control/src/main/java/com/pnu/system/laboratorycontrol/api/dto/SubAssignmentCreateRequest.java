package com.pnu.system.laboratorycontrol.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class SubAssignmentCreateRequest {

    @NotBlank
    private String assignmentId;
    @NotBlank
    private String title;
    private String description;
    private Integer maxGrade;
    @Min(1)
    @Max(100)
    private Integer weight;
    private ZonedDateTime deadline;

}
