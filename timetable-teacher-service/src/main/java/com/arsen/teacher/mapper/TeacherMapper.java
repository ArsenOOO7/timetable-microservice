package com.arsen.teacher.mapper;

import com.arsen.common.event.EntityStatus;
import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.domain.TeacherDocument;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherResponseDto;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import com.arsen.teacher.event.TeacherUpdateEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TeacherMapper {

    Teacher fromDto(TeacherDto teacherDto);
    TeacherResponseDto toDto(Teacher teacher);

    TeacherUpdateEvent toEvent(Teacher teacher, EntityStatus status);

    @Mapping(target = "fullName", expression = "java(teacher.toString())")
    TeacherDocument toDocument(Teacher teacher);

    default TeacherResultSearchDto toDto(TeacherDocument teacherDocument){
        String[] names = teacherDocument.getFullName().split(" ");
        return new TeacherResultSearchDto(teacherDocument.getId(), names[1], names[0], names[2]);
    }

    List<TeacherResultSearchDto> toDtos(List<TeacherDocument> teacherDocuments);

}
