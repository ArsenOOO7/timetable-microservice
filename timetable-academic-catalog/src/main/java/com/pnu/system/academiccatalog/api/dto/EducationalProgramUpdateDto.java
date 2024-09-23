package com.pnu.system.academiccatalog.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationalProgramUpdateDto {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String specialtyId;
    @NotNull
    private Integer version;

}
