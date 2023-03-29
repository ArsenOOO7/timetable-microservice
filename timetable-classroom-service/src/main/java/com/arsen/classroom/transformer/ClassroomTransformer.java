package com.arsen.classroom.transformer;

import com.arsen.classroom.domain.Classroom;
import com.arsen.classroom.dto.ClassroomDto;
import com.arsen.classroom.event.ClassroomUpdateEvent;
import com.arsen.common.event.EntityStatus;

public class ClassroomTransformer {

    public static Classroom convertDtoToEntity(ClassroomDto classroomDto){
        return new Classroom(
                classroomDto.getId(),
                classroomDto.getName(),
                classroomDto.getAddress()
        );
    }


    public static ClassroomDto convertEntityToDto(Classroom classroom){
        return new ClassroomDto(
                classroom.getId(),
                classroom.getName(),
                classroom.getAddress()
        );
    }


    public static ClassroomUpdateEvent convertClassroomToUpdateEvent(Classroom classroom, EntityStatus status){
        return new ClassroomUpdateEvent(
                classroom.getId(),
                classroom.getName(),
                classroom.getAddress(),
                status
        );
    }

    public static ClassroomUpdateEvent convertClassroomToUpdateEvent(ClassroomDto classroom, EntityStatus status){
        return new ClassroomUpdateEvent(
                classroom.getId(),
                classroom.getName(),
                classroom.getAddress(),
                status
        );
    }


    public static void copyValues(Classroom classroom, ClassroomDto classroomDto){
        classroom.setName(classroom.getName());
        classroom.setAddress(classroom.getAddress());
    }

}
