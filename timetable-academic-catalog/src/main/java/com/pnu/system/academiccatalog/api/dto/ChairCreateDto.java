package com.pnu.system.academiccatalog.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChairCreateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String shortName;
    @NotBlank
    private String departmentId;
    private List<String> specialtiesIds;

}
