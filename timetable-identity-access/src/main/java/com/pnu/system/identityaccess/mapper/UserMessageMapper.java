package com.pnu.system.identityaccess.mapper;

import com.pnu.system.common.messaging.model.EntityDeleteMessage;
import com.pnu.system.common.messaging.model.EntityUpdateMessage;
import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.identityaccess.domain.UserWithTeacherProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMessageMapper {

    @Mapping(target = "type", constant = "USER")
    @Mapping(target = "body", source = "user")
    EntityUpdateMessage<UserSnapshotDto> asUserUpdateMessage(UserWithTeacherProfile user);

    @Mapping(target = "type", constant = "USER")
    EntityDeleteMessage asUserDeleteMessage(String id);

}
