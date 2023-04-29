package com.arsen.classroom.mapper;

import com.arsen.classroom.domain.Classroom;
import com.arsen.classroom.dto.ClassroomDto;
import com.arsen.classroom.dto.ClassroomResponseDto;
import com.arsen.classroom.event.ClassroomUpdateEvent;
import com.arsen.common.event.EntityStatus;
import org.mapstruct.Mapper;

@Mapper
public interface ClassroomMapper {

    Classroom fromDto(ClassroomDto classroomDto);
    ClassroomResponseDto toDto(Classroom classroom);
    ClassroomUpdateEvent toEvent(Classroom classroom, EntityStatus status);

}
