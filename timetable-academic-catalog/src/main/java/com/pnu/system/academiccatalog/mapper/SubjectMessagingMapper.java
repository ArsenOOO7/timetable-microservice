package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.academiccatalog.domain.Subject;
import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMessagingMapper {

    @Mapping(target = "type", constant = "SUBJECT")
    @Mapping(target = "body", source = "subject")
    EntityUpdateMessage<SubjectSnapshotDto> asSubjectUpdateMessage(Subject subject);

    @Mapping(target = "type", constant = "SUBJECT")
    EntityDeleteMessage asSubjectDeleteMessage(String id);

}
