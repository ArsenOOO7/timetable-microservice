package com.arsen.subject.transform;

import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectDto;
import com.arsen.subject.event.EntityStatus;
import com.arsen.subject.event.SubjectUpdateEvent;

public class SubjectTransformer {

    public static Subject convertSubjectDtoToEntity(SubjectDto subjectDto){
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setSubjectName(subjectDto.getSubjectName());
        return subject;
    }


    public static SubjectDto convertSubjectToDto(Subject subject){
        return new SubjectDto(subject.getId(), subject.getSubjectName());
    }

    public static SubjectUpdateEvent convertSubjectToUpdateEvent(Subject subject, EntityStatus status){
        return new SubjectUpdateEvent(subject.getId(), subject.getSubjectName(), status);
    }
}
