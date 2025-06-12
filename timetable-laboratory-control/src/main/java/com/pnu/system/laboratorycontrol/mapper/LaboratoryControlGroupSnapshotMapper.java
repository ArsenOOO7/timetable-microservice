package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.laboratorycontrol.api.dto.CourseGroupDto;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlGroupSnapshot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LaboratoryControlGroupSnapshotMapper {

    LaboratoryControlGroupSnapshot asLaboratoryControlGroupSnapshot(GroupSnapshotDto groupSnapshotDto);

    List<CourseGroupDto> asCourseGroupDtos(List<LaboratoryControlGroupSnapshot> groups);

}
