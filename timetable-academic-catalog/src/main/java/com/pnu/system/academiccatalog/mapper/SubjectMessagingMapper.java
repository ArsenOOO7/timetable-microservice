package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.common.messaging.mapper.BaseMessagingMapper;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMessagingMapper extends BaseMessagingMapper<SubjectSnapshotDto> {


}
