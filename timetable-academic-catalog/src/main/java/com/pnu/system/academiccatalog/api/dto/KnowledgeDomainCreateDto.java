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
public class KnowledgeDomainCreateDto {

    @NotBlank
    private String code;
    @NotBlank
    private String name;

}
