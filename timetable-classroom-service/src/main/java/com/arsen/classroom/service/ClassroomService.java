package com.arsen.classroom.service;

import com.arsen.classroom.domain.Classroom;
import com.arsen.classroom.dto.ClassroomDto;
import com.arsen.classroom.dto.ClassroomResponseDto;
import com.arsen.classroom.mapper.ClassroomMapper;
import com.arsen.classroom.repository.ClassroomRepository;
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
    private final ClassroomMapper mapper;


    public Classroom readById(long id){
        return classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with id " + id + " not found!"));
    }

    public ClassroomResponseDto readById(long id, boolean dto){
        return classroomRepository.findDtoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with id " + id + " not found!"));
    }



    public ClassroomResponseDto create(ClassroomDto classroomDto){

        if(classroomDto == null){
            throw new NullPointerException("Classroom cannot be null!");
        }

        Classroom classroom = mapper.fromDto(classroomDto);
        classroom = classroomRepository.save(classroom);

        postUpdate(classroom, EntityStatus.CREATED);

        return mapper.toDto(classroom);
    }


    public void update(long id, ClassroomDto classroomDto){

        if(classroomDto == null){
            throw new NullPointerException("Classroom cannot be null!");
        }

        Classroom classroom = readById(id);
        classroom.setName(classroomDto.getName());
        classroom.setAddress(classroomDto.getAddress());

        postUpdate(classroomRepository.save(classroom), EntityStatus.UPDATED);

    }


    public void delete(long id){
        Classroom classroom = readById(id);
        classroomRepository.delete(classroom);
        postUpdate(classroom, EntityStatus.DELETED);
    }


    private void postUpdate(Classroom classroom, EntityStatus entityStatus){
        streamBridge.send("classroom-topic", mapper.toEvent(classroom, entityStatus));
    }

    public List<ClassroomResponseDto> search(SearchDto dto) {
        return classroomRepository.findAllByName(dto.getSearchQuery());
    }
}
