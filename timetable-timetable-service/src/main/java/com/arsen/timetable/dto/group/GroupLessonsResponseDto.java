package com.arsen.timetable.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupLessonsResponseDto {

    private long groupId;
    private Set<Long> lessonIds;

}
