package com.pnu.system.academiccatalog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectUpdateDto {
    private String id;
    private String name;
    private String educationalProgramId;
    private Integer version;
}
