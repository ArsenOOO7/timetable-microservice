package com.pnu.system.academiccatalog.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyUpdateDto {

    @NotBlank
    private String id;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String shortName;
    @NotBlank
    private String knowledgeDomainId;
    @NotNull
    private Integer version;

}
