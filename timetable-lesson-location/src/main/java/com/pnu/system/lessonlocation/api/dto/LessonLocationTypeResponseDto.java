package com.pnu.system.lessonlocation.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessonLocationTypeResponseDto {
    private String id;
    private String name;
    private String shortName;
    private Integer version;
}
