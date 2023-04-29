package com.arsen.subject.service;

import com.arsen.common.dto.SearchDto;
import com.arsen.common.event.EntityStatus;
import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectDto;
import com.arsen.subject.dto.SubjectResponseDto;
import com.arsen.subject.mapper.SubjectMapper;
import com.arsen.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final StreamBridge streamBridge;
    private final SubjectRepository subjectRepository;
    private final SubjectMapper mapper;


    public Subject readById(long id){
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " is not found!"));
    }

    public SubjectResponseDto readById(long id, boolean dto){
        return subjectRepository.findDtoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " is not found!"));
    }

    public SubjectResponseDto create(SubjectDto subjectDto){

        if(subjectDto == null){
            throw new EntityNullReferenceException("Subject cannot be null!");
        }

        Subject subject = mapper.fromDto(subjectDto);
        subject = subjectRepository.save(subject);

        postUpdate(subject, EntityStatus.CREATED);

        return mapper.toDto(subject);
    }

    public void update(long id, SubjectDto subjectDto){

        if(subjectDto == null){
            throw new EntityNullReferenceException("Subject cannot be null!");
        }

        Subject subject = readById(id);
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
        streamBridge.send("subject-topic", mapper.toEvent(subject, entityStatus));
    }

    public List<SubjectResponseDto> search(SearchDto dto) {
        return subjectRepository.findAllByQuery(dto.getSearchQuery());
    }
}
