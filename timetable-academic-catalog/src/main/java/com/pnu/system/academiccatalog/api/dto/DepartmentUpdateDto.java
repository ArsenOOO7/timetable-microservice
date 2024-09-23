package com.pnu.system.academiccatalog.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateDto {
    private String id;
    private String name;
    private String shortName;
    private Integer version;
}
