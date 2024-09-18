package com.pnu.system.lessonlocation.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonLocationUpdateRequest {
    private String id;
    private String locationTypeId;
    private String name;
    private String address;
    private Integer version;
}
