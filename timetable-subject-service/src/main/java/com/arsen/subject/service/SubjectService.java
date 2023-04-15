package com.arsen.subject.service;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectDto;
import com.arsen.common.event.EntityStatus;
import com.arsen.subject.repository.SubjectRepository;
import com.arsen.subject.transform.SubjectTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final StreamBridge streamBridge;
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
            throw new EntityNullReferenceException("Subject cannot be null!");
        }

        Subject subject = SubjectTransformer.convertSubjectDtoToEntity(subjectDto);
        subjectDto = SubjectTransformer.convertSubjectToDto(subjectRepository.save(subject));

        postUpdate(subject, EntityStatus.CREATED);

        return subjectDto;
    }

    public void update(SubjectDto subjectDto){

        if(subjectDto == null){
            throw new EntityNullReferenceException("Subject cannot be null!");
        }

        Subject subject = readById(subjectDto.getId());
        subject.setSubjectName(subjectDto.getSubjectName());
        subjectRepository.save(subject);

        postUpdate(subject, EntityStatus.UPDATED);


    }

    public void delete(long id){
        Subject subject = readById(id);
        subjectRepository.delete(subject);
        postUpdate(subject, EntityStatus.DELETED);
    }

    private void postUpdate(Subject subject, EntityStatus entityStatus){
        System.out.println("Add new subject");
        streamBridge.send("subject-topic", SubjectTransformer.convertSubjectToUpdateEvent(subject, entityStatus));
    }
}
