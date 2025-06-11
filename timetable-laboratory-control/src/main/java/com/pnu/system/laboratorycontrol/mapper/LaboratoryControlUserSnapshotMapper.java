package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.dto.UserDto;
import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlUserSnapshot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LaboratoryControlUserSnapshotMapper {

    LaboratoryControlUserSnapshot asLaboratoryControlUserSnapshot(UserSnapshotDto userSnapshotDto);

    List<UserDto> asUserDtos(List<LaboratoryControlUserSnapshot> users);

}
