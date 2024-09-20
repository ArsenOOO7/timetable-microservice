package com.pnu.system.lessonlocation.mapper;

import com.pnu.system.lessonlocation.api.dto.LessonLocationCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationUpdateRequest;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import org.mapstruct.Mapper;

@Mapper(uses = {LocationTypeMapper.class})
public interface LessonLocationMapper {
    LessonLocation asLessonLocation(LessonLocationCreateRequest lessonLocationCreateRequest);

    LessonLocation asLessonLocation(LessonLocationUpdateRequest lessonLocationUpdateRequest);

    LessonLocationResponseDto asLessonLocationDto(LessonLocation lessonLocation);
}
