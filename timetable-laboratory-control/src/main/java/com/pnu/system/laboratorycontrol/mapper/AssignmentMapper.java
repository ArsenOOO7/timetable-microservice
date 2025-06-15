package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.laboratorycontrol.api.dto.AssignmentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentDto;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentUpdateRequest;
import com.pnu.system.laboratorycontrol.domain.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {

    @Mapping(target = "visibility", defaultValue = "VISIBLE")
    Assignment asAssignment(AssignmentCreateRequest request);

    @Mapping(target = "id", ignore = true)
    void applyUpdateRequest(@MappingTarget Assignment assignment, AssignmentUpdateRequest request);

    AssignmentDto asAssignmentDto(Assignment assignment);

}
