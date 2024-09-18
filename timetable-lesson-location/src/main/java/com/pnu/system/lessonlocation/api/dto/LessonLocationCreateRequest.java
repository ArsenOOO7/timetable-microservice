package com.pnu.system.lessonlocation.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonLocationCreateRequest {
    private String locationTypeId;
    private String name;
    private String address;
}
