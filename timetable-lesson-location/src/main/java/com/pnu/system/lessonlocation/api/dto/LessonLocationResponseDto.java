package com.pnu.system.lessonlocation.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessonLocationResponseDto {
    private String id;
    private LessonLocationTypeResponseDto locationType;
    private String name;
    private String address;
    private Integer version;
}
