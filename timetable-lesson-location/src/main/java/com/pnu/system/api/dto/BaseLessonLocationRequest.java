package com.pnu.system.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseLessonLocationRequest {
    private String locationTypeId;
    private String name;
    private String address;
}
