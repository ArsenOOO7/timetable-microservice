package com.pnu.system.lessonlocation.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessonLocationDto {
    private String id;
    private LessonLocationTypeDto locationType;
    private String name;
    private String address;
    private Integer version;
}
