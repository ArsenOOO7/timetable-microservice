package com.pnu.system.academiccatalog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationalProgramPreviewDto {

    private String id;
    private String name;
    private SpecialtyPreviewDto specialty;

    public EducationalProgramPreviewDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
