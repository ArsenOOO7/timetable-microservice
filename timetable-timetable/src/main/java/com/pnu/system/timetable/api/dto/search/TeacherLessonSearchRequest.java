package com.pnu.system.timetable.api.dto.search;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherLessonSearchRequest extends BaseLessonSearchRequest {

    @NotBlank
    private String teacherId;

}
