package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionDto;
import com.pnu.system.laboratorycontrol.constant.AssignmentType;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AssignmentDto extends VersionDto {

    private String title;
    private String description;
    private ZonedDateTime deadline;
    private AssignmentType type;
    private Integer maxGrade;

}
