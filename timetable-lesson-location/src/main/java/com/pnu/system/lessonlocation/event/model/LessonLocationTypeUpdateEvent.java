package com.pnu.system.lessonlocation.event.model;

import com.pnu.system.lessonlocation.domain.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessonLocationTypeUpdateEvent {

    private LocationType type;

}
