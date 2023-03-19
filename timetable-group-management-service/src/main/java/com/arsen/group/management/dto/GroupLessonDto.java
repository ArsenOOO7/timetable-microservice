package com.arsen.group.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupLessonDto {

    private long groupId;
    private long lessonId;

    private LocalDate lessonDate;

}
