package com.pnu.system.academiccatalog.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCreateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String shortName;

}
