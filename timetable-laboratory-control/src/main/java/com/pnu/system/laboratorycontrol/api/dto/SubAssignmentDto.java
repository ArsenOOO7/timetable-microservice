package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionDto;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class SubAssignmentDto extends VersionDto {

    private String assignmentId;
    private String title;
    private String description;
    private Integer maxGrade;
    private Integer weight;
    private ZonedDateTime deadline;

}
