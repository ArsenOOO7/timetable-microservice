package com.pnu.system.group.mapper;

import com.pnu.system.common.messaging.mapper.BaseMessagingMapper;
import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMessagingMapper extends BaseMessagingMapper<GroupSnapshotDto> {


}
