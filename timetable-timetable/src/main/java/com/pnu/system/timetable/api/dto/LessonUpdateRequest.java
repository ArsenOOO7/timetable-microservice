package com.pnu.system.timetable.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class LessonUpdateRequest {

    @NotBlank
    private String id;

    @NotNull
    private LocalDate date;
    @Max(8)
    @Min(1)
    private short number;

    private String lessonLocationId;
    @NotBlank
    private String teacherId;
    @NotBlank
    private String subjectId;
    @NotBlank
    private String typeId;

    @NotNull
    private Boolean online;

    @NotEmpty
    private Set<String> groupIds;

    @NotNull
    private Integer version;

}
