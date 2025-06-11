package com.pnu.system.laboratorycontrol.api.dto;

import com.pnu.system.common.dto.VersionDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursePrimaryLinkDto extends VersionDto {

    private String link;
    private String label;

}
