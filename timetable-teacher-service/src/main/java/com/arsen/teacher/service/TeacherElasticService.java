package com.arsen.teacher.service;

import com.arsen.common.dto.SearchDto;
import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.teacher.domain.Teacher;
import com.arsen.teacher.domain.TeacherDocument;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import com.arsen.teacher.mapper.TeacherMapper;
import com.arsen.teacher.repository.TeacherElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherElasticService {

    private final TeacherElasticRepository repository;
    private final TeacherMapper mapper;

    public void create(Teacher teacher){
        repository.save(mapper.toDocument(teacher));
    }

    @Transactional
    public void update(Teacher teacher){
        TeacherDocument teacherDocument = repository.findById(teacher.getId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + teacher.getId() + " is not found!"));
        teacherDocument.setFullName(teacher.toString());
    }


    public void delete(long id){
        repository.deleteById(id);
    }

    public List<TeacherResultSearchDto> searchTeachers(SearchDto searchDto){
        return mapper.toDtos(repository.findAllByFullNameLike(searchDto.getSearchQuery()));
    }

}
