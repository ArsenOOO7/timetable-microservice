package com.arsen.teacher.service;

import com.arsen.common.dto.SearchDto;
import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import com.arsen.common.event.EntityStatus;
import com.arsen.teacher.repository.TeacherRepository;
import com.arsen.teacher.transformer.TeacherTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final StreamBridge streamBridge;
    private final TeacherRepository teacherRepository;

    public Teacher readById(long id){
        return teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " is not found!"));
    }

    public TeacherDto readById(long id, boolean dto){
        return teacherRepository.findDtoById(id)
            .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " is not found!"));
    }

    public List<TeacherResultSearchDto> findAllByQuery(SearchDto searchDto){
        return teacherRepository.findAllByQuery(searchDto.getSearchQuery());
    }

    public TeacherDto create(TeacherDto teacherDto){

        if(teacherDto == null){
            throw new EntityNullReferenceException("Teacher cannot be null!");
        }

        teacherDto = TeacherTransformer.convertTeacherToDto(teacherRepository.save(TeacherTransformer.convertTeacherDtoToEntity(teacherDto)));
        postUpdate(teacherDto, EntityStatus.CREATED);
        return teacherDto;
    }


    public void update(TeacherDto teacherDto){

        if(teacherDto == null){
            throw new EntityNullReferenceException("Teacher cannot be null!");
        }

        postUpdate(teacherRepository.save(TeacherTransformer.convertTeacherDtoToEntity(teacherDto)), EntityStatus.UPDATED);

    }


    public void delete(long id){
        Teacher teacher = readById(id);
        teacherRepository.delete(teacher);
        postUpdate(teacher, EntityStatus.DELETED);
    }

    private void postUpdate(Teacher teacher, EntityStatus status){
        streamBridge.send("teacher-topic", TeacherTransformer.convertTeacherToUpdateEvent(teacher, status));
    }

    private void postUpdate(TeacherDto teacher, EntityStatus status){
        streamBridge.send("teacher-topic", TeacherTransformer.convertTeacherToUpdateEvent(teacher, status));
    }
}
