package com.pnu.system.academiccatalog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyResponseDto {

    private String id;
    private String code;
    private String name;
    private String shortName;
    private String knowledgeDomainId;
    ;
    private Integer version;

}
