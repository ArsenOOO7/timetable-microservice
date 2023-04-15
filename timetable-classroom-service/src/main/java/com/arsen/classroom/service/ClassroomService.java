package com.arsen.classroom.service;

import com.arsen.classroom.domain.Classroom;
import com.arsen.classroom.dto.ClassroomDto;
import com.arsen.classroom.repository.ClassroomRepository;
import com.arsen.classroom.transformer.ClassroomTransformer;
import com.arsen.common.dto.SearchDto;
import com.arsen.common.event.EntityStatus;
import com.arsen.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final StreamBridge streamBridge;
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

        classroomDto = ClassroomTransformer.convertEntityToDto(
            classroomRepository.save(ClassroomTransformer.convertDtoToEntity(classroomDto))
        );

        postUpdate(classroomDto, EntityStatus.CREATED);

        return classroomDto;
    }


    public void update(ClassroomDto classroomDto){

        if(classroomDto == null){
            throw new NullPointerException("Classroom cannot be null!");
        }

        Classroom classroom = readById(classroomDto.getId());
        ClassroomTransformer.copyValues(classroom, classroomDto);

        postUpdate(classroomRepository.save(classroom), EntityStatus.UPDATED);

    }


    public void delete(long id){
        Classroom classroom = readById(id);
        classroomRepository.delete(classroom);
        postUpdate(classroom, EntityStatus.DELETED);
    }


    private void postUpdate(Classroom classroom, EntityStatus entityStatus){
        streamBridge.send("classroom-topic", ClassroomTransformer.convertClassroomToUpdateEvent(classroom, entityStatus));
    }

    private void postUpdate(ClassroomDto classroom, EntityStatus entityStatus){
        streamBridge.send("classroom-topic", ClassroomTransformer.convertClassroomToUpdateEvent(classroom, entityStatus));
    }


    public List<ClassroomDto> search(SearchDto dto) {
        return classroomRepository.findAllByName(dto.getSearchQuery());
    }
}
