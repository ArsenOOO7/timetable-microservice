package com.pnu.system.academiccatalog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChairResponseDto {

    private String id;
    private String name;
    private String shortName;
    private DepartmentResponseDto department;
    private List<SpecialtyResponseDto> specialties;
    private Integer version;

}
