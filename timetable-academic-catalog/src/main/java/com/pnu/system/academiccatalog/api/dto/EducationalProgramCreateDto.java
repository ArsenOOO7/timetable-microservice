package com.pnu.system.academiccatalog.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationalProgramCreateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String specialtyId;

}
