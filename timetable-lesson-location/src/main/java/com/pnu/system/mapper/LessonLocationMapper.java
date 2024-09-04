package com.pnu.system.mapper;

import com.pnu.system.api.dto.LessonLocationCreateRequest;
import com.pnu.system.domain.LessonLocation;
import org.mapstruct.Mapper;

@Mapper
public interface LessonLocationMapper {

    LessonLocation toLessonLocation(LessonLocationCreateRequest lessonLocationCreateRequest);
}
