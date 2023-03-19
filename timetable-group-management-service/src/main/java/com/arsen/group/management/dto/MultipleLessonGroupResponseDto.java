package com.arsen.group.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class MultipleLessonGroupResponseDto {

    private long lessonId;
    private Set<GroupResponseDto> groups;

}
