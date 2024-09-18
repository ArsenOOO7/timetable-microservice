package com.pnu.system.lessonlocation.mapper;

import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeCreateDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeUpdateDto;
import com.pnu.system.lessonlocation.domain.LocationType;
import org.mapstruct.Mapper;

@Mapper
public interface LocationTypeMapper {

    LocationType asLocationType(LessonLocationTypeCreateDto locationType);

    LocationType asLocationType(LessonLocationTypeUpdateDto locationType);

    LessonLocationTypeDto asLessonLocationTypeDto(LocationType locationType);

}
