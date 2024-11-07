package com.pnu.system.lessonlocation.mapper;

import com.pnu.system.common.messaging.mapper.BaseMessagingMapper;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonLocationMessageMapper extends BaseMessagingMapper<LessonLocationSnapshotDto> {


}
