package com.pnu.system.identityaccess.mapper;

import com.pnu.system.common.messaging.mapper.BaseMessagingMapper;
import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMessagingMapper extends BaseMessagingMapper<UserSnapshotDto> {


}
