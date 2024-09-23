package com.pnu.system.academiccatalog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPreviewDto {

    private String id;
    private String name;
    private EducationalProgramPreviewDto educationalProgram;

    public SubjectPreviewDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
