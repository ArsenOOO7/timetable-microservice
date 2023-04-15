package com.arsen.timetable.service.readonly;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.timetable.domain.readonly.SubjectRead;
import com.arsen.timetable.dto.readonly.SubjectDto;
import com.arsen.timetable.event.SubjectUpdateEvent;
import com.arsen.timetable.repository.SubjectReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectReadService{

    private final SubjectReadRepository subjectReadRepository;


    public SubjectRead readById(long id){
        return subjectReadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " not found!"));
    }

    public void synchronize(SubjectUpdateEvent subjectUpdateEvent){
        switch(subjectUpdateEvent.getStatus()){
            case CREATED -> create(subjectUpdateEvent);
            case UPDATED -> update(subjectUpdateEvent);
            case DELETED -> delete(subjectUpdateEvent.getId());
        }
    }

    public void create(SubjectDto subjectDto){

        if(subjectDto == null){
            throw new EntityNullReferenceException("Subject cannot be null!");
        }

        subjectReadRepository.save(
                new SubjectRead(subjectDto.getId(), subjectDto.getSubjectName())
        );

    }

    public void update(SubjectDto subjectDto){

        if(subjectDto == null){
            throw new EntityNullReferenceException("Subject cannot be null!");
        }

        SubjectRead subjectRead = readById(subjectDto.getId());
        subjectRead.setSubjectName(subjectDto.getSubjectName());

        subjectReadRepository.save(subjectRead);
    }


    public void delete(long id){
        subjectReadRepository.deleteById(id);
    }

}
