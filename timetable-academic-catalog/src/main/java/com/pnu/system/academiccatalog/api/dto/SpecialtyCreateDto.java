package com.pnu.system.academiccatalog.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyCreateDto {

    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String shortName;
    @NotBlank
    private String knowledgeDomainId;

}
