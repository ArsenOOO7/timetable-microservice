package com.pnu.system.group.mapper;

import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.group.domain.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMessageMapper {

    @Mapping(target = "type", constant = "GROUP")
    @Mapping(target = "body", source = "group")
    EntityUpdateMessage<GroupSnapshotDto> asGroupUpdateMessage(Group group);

    @Mapping(target = "type", constant = "GROUP")
    EntityDeleteMessage asGroupDeleteMessage(String id);

}
