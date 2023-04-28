package com.arsen.teacher.mapper;

import com.arsen.common.event.EntityStatus;
import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherResponseDto;
import com.arsen.teacher.event.TeacherUpdateEvent;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {

    Teacher fromDto(TeacherDto teacherDto);
    TeacherResponseDto toDto(Teacher teacher);

    TeacherUpdateEvent toEvent(Teacher teacher, EntityStatus status);

}
