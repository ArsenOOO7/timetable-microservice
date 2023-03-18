package com.arsen.teacher.service;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherQueryDto;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import com.arsen.teacher.repository.TeacherRepository;
import com.arsen.teacher.transformer.TeacherTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher readById(long id){
        return teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " is not found!"));
    }

    public TeacherDto readById(long id, boolean dto){
        return teacherRepository.findDtoById(id)
            .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " is not found!"));
    }

    public List<TeacherResultSearchDto> findAllByQuery(TeacherQueryDto teacherQueryDto){
        return teacherRepository.findAllByQuery(teacherQueryDto.getSearchQuery());
    }

    public TeacherDto create(TeacherDto teacherDto){

        if(teacherDto == null){
            throw new NullPointerException("Teacher cannot be null!");
        }

        return TeacherTransformer.convertTeacherToDto(teacherRepository.save(TeacherTransformer.convertTeacherDtoToEntity(teacherDto)));

    }


    public void update(TeacherDto teacherDto){

        if(teacherDto == null){
            throw new NullPointerException("Teacher cannot be null!");
        }

        teacherRepository.save(TeacherTransformer.convertTeacherDtoToEntity(teacherDto));

    }


    public void delete(long id){
        teacherRepository.delete(readById(id));
    }

}
