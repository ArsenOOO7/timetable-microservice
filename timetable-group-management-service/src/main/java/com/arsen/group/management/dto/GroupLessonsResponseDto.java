package com.arsen.group.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupLessonsResponseDto {

    private long groupId;
    private Set<Long> lessonIds;

}
