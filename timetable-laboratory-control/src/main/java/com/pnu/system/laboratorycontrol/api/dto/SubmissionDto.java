package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionDto;
import com.pnu.system.laboratorycontrol.constant.SubmissionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class SubmissionDto extends VersionDto {

    private Integer grade;
    private SubmissionStatus status;
    private ZonedDateTime submitDate;

}
