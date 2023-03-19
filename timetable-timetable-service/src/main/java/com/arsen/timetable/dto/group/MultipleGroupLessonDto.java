package com.arsen.timetable.dto.group;

import com.arsen.timetable.dto.group.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class MultipleGroupLessonDto {

    private long id;
    private Set<GroupDto> groups;

}
