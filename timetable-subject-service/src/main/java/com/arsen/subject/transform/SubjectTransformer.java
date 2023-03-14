package com.arsen.subject.transform;

import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectDto;

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

}
