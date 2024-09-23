package com.pnu.system.academiccatalog.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KnowledgeDomainUpdateDto {
    private String id;
    private String code;
    private String name;
    private Integer version;
}
