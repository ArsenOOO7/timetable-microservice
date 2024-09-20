package com.pnu.system.lessonlocation.mapper;

import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeUpdateDto;
import com.pnu.system.lessonlocation.domain.LocationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationTypeMapper {

    LocationType asLocationType(LessonLocationTypeCreateRequest locationType);

    LocationType asLocationType(LessonLocationTypeUpdateDto locationType);

    LessonLocationTypeResponseDto asLessonLocationTypeDto(LocationType locationType);

}
