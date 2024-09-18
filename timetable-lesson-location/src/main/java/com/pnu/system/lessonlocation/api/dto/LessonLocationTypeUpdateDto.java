package com.pnu.system.lessonlocation.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonLocationTypeUpdateDto {
    private String id;
    private String name;
    private String shortName;
    private Integer version;
}
