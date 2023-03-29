package com.arsen.teacher.transformer;

import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.common.event.EntityStatus;
import com.arsen.teacher.event.TeacherUpdateEvent;

public class TeacherTransformer {

    public static TeacherDto convertTeacherToDto(Teacher teacher){
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getFatherName(),
                teacher.getEmail(),
                teacher.getMeetingLink());
    }


    public static Teacher convertTeacherDtoToEntity(TeacherDto teacherDto){
        return new Teacher(
                teacherDto.getId(),
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getFatherName(),
                teacherDto.getEmail(),
                teacherDto.getMeetingLink());
    }


    public static TeacherUpdateEvent convertTeacherToUpdateEvent(Teacher teacher, EntityStatus status){
        return new TeacherUpdateEvent(
            teacher.getId(),
            teacher.getFirstName(),
            teacher.getLastName(),
            teacher.getFatherName(),
            teacher.getMeetingLink(),
            status
        );
    }

    public static TeacherUpdateEvent convertTeacherToUpdateEvent(TeacherDto teacher, EntityStatus status){
        return new TeacherUpdateEvent(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getFatherName(),
                teacher.getMeetingLink(),
                status
        );
    }

}
