package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.annotation.MapperIgnoreAuditableFields;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentDto;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentUpdateRequest;
import com.pnu.system.laboratorycontrol.domain.Assignment;
import com.pnu.system.laboratorycontrol.domain.SubAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SubAssignmentMapper {

    @MapperIgnoreAuditableFields
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "weight", constant = "100")
    @Mapping(target = "assignmentId", source = "assignment.id")
    SubAssignment asSubAssignment(Assignment assignment);

    SubAssignment asSubAssignment(SubAssignmentCreateRequest request);

    @Mapping(target = "id", ignore = true)
    void applySubAssignmentUpdateRequest(@MappingTarget SubAssignment subAssignment, SubAssignmentUpdateRequest request);

    SubAssignmentDto asSubAssignmentDto(SubAssignment subAssignment);

    List<SubAssignmentDto> asSubAssignmentDtos(Collection<SubAssignment> subAssignment);

}
