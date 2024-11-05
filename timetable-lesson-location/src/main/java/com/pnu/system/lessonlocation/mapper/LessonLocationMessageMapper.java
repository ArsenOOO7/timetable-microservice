package com.pnu.system.lessonlocation.mapper;

import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonLocationMessageMapper {

    @Mapping(target = "typeShortName", source = "locationType.shortName")
    LessonLocationSnapshotDto asLessonLocationSnapshotDto(LessonLocation location);

    @Mapping(target = "type", constant = "LESSON_LOCATION")
    @Mapping(target = "body", source = "location")
    EntityUpdateMessage<LessonLocationSnapshotDto> asLessonLocationUpdateMessage(LessonLocation location);

    @Mapping(target = "type", constant = "LESSON_LOCATION")
    EntityDeleteMessage asLessonLocationDeleteMessage(String id);

}
