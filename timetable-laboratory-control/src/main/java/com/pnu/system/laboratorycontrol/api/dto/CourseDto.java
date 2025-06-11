package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionDto;
import com.pnu.system.laboratorycontrol.constant.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto extends VersionDto {

    private String title;
    private String description;
    private CourseStatus status;
    private CourseSubjectDto subject;

}
