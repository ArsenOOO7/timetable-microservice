package com.arsen.teacher.service;

import com.arsen.common.dto.SearchDto;
import com.arsen.common.event.EntityStatus;
import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherResponseDto;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import com.arsen.teacher.mapper.TeacherMapper;
import com.arsen.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final StreamBridge streamBridge;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper mapper;

    public Teacher readById(long id){
        return teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " is not found!"));
    }

    public TeacherResponseDto readById(long id, boolean dto){
        return teacherRepository.findDtoById(id)
            .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " is not found!"));
    }

    public List<TeacherResultSearchDto> findAllByQuery(SearchDto searchDto){
        return teacherRepository.findAllByQuery(searchDto.getSearchQuery());
    }

    public TeacherResponseDto create(TeacherDto teacherDto){

        if(teacherDto == null){
            throw new EntityNullReferenceException("Teacher cannot be null!");
        }

        Teacher teacher = mapper.fromDto(teacherDto);
        teacher = teacherRepository.save(teacher);

        postUpdate(teacher, EntityStatus.CREATED);

        return mapper.toDto(teacher);
    }


    public void update(long id, TeacherDto teacherDto){

        if(teacherDto == null){
            throw new EntityNullReferenceException("Teacher cannot be null!");
        }

        Teacher teacher = readById(id);
        teacher = teacher.toBuilder()
                        .firstName(teacherDto.getFirstName())
                        .lastName(teacherDto.getLastName())
                        .fatherName(teacherDto.getFatherName())
                        .email(teacherDto.getEmail())
                        .meetingLink(teacherDto.getMeetingLink())
                .build();

        postUpdate(teacherRepository.save(teacher), EntityStatus.UPDATED);

    }


    public void delete(long id){
        Teacher teacher = readById(id);
        teacherRepository.delete(teacher);
        postUpdate(teacher, EntityStatus.DELETED);
    }

    private void postUpdate(Teacher teacher, EntityStatus status){
        streamBridge.send("teacher-topic", mapper.toEvent(teacher, status));
    }
}
