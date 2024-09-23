package com.pnu.system.academiccatalog.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChairUpdateDto {

    private String id;
    private String name;
    private String shortName;
    private String departmentId;
    private List<String> specialtiesIds;
    private Integer version;

}
