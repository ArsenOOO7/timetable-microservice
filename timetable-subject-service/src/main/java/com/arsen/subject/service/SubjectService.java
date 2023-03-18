package com.arsen.subject.service;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectDto;
import com.arsen.subject.repository.SubjectRepository;
import com.arsen.subject.transform.SubjectTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;


    public Subject readById(long id){
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " is not found!"));
    }

    public SubjectDto readById(long id, boolean dto){
        return subjectRepository.findDtoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " is not found!"));
    }

    public SubjectDto create(SubjectDto subjectDto){

        if(subjectDto == null){
            throw new NullPointerException("Subject cannot be null!");
        }

        Subject subject = SubjectTransformer.convertSubjectDtoToEntity(subjectDto);
        return SubjectTransformer.convertSubjectToDto(subjectRepository.save(subject));

    }

    public void update(SubjectDto subjectDto){

        if(subjectDto == null){
            throw new NullPointerException("Subject cannot be null!");
        }

        Subject subject = readById(subjectDto.getId());
        subject.setSubjectName(subjectDto.getSubjectName());
        subjectRepository.save(subject);

    }

    public void delete(long id){
        subjectRepository.delete(readById(id));
    }
}
