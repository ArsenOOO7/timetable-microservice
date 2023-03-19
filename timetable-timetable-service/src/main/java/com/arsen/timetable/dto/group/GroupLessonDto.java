package com.arsen.timetable.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class GroupLessonDto {

    private long groupId;
    private long lessonId;

    private LocalDate lessonDate;

}
