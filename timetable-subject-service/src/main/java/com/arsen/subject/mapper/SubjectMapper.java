package com.arsen.subject.mapper;

import com.arsen.common.event.EntityStatus;
import com.arsen.subject.domain.Subject;
import com.arsen.subject.dto.SubjectDto;
import com.arsen.subject.dto.SubjectResponseDto;
import com.arsen.subject.event.SubjectUpdateEvent;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectMapper {

    Subject fromDto(SubjectDto dto);
    SubjectResponseDto toDto(Subject subject);

    SubjectUpdateEvent toEvent(Subject subject, EntityStatus status);

}
