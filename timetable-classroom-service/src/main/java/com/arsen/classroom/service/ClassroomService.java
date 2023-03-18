package com.arsen.classroom.service;

import com.arsen.classroom.domain.Classroom;
import com.arsen.classroom.dto.ClassroomDto;
import com.arsen.classroom.repository.ClassroomRepository;
import com.arsen.classroom.transformer.ClassroomTransformer;
import com.arsen.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public Classroom readById(long id){
        return classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with id " + id + " not found!"));
    }

    public ClassroomDto readById(long id, boolean dto){
        return classroomRepository.findDtoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with id " + id + " not found!"));
    }



    public ClassroomDto create(ClassroomDto classroomDto){

        if(classroomDto == null){
            throw new NullPointerException("Classroom cannot be null!");
        }

        return ClassroomTransformer.convertEntityToDto(
            classroomRepository.save(ClassroomTransformer.convertDtoToEntity(classroomDto))
        );
    }


    public void update(ClassroomDto classroomDto){

        if(classroomDto == null){
            throw new NullPointerException("Classroom cannot be null!");
        }

        Classroom classroom = readById(classroomDto.getId());
        ClassroomTransformer.copyValues(classroom, classroomDto);

        classroomRepository.save(classroom);

    }


    public void delete(long id){
        classroomRepository.delete(readById(id));
    }

}
