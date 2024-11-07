package com.pnu.system.timetable.api.dto.search;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationLessonSearchRequest extends BaseLessonSearchRequest {

    @NotBlank
    private String locationId;

}
