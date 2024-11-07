package com.pnu.system.timetable.mapper;

import com.pnu.system.timetable.api.dto.LessonTypeCreateRequest;
import com.pnu.system.timetable.api.dto.LessonTypeResponseDto;
import com.pnu.system.timetable.api.dto.LessonTypeUpdateRequest;
import com.pnu.system.timetable.domain.LessonType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonTypeMapper {

    LessonType asLessonType(LessonTypeCreateRequest request);

    LessonType asLessonType(LessonTypeUpdateRequest request);

    LessonTypeResponseDto asLessonTypeResponseDto(LessonType type);

    List<LessonTypeResponseDto> asLessonTypeResponseDtos(List<LessonType> types);

}
