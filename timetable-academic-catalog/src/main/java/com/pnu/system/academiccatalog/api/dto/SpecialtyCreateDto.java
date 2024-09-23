package com.pnu.system.academiccatalog.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyCreateDto {

    private String code;
    private String name;
    private String shortName;
    private String knowledgeDomainId;

}
