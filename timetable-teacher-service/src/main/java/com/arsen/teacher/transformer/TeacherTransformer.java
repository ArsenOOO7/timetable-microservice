package com.arsen.teacher.transformer;

import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.dto.TeacherDto;

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

}
