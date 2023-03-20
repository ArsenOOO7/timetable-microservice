package com.arsen.group.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LessonGroupResponseDto {

    private long lessonId;
    private GroupResponseDto groupLessonDto;

}
