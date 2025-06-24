package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.annotation.MapperIgnoreAuditableFields;
import com.pnu.system.common.utils.TimetableUserUtils;
import com.pnu.system.laboratorycontrol.api.dto.SubmissionDto;
import com.pnu.system.laboratorycontrol.domain.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", imports = {TimetableUserUtils.class})
public interface SubmissionMapper {

    @MapperIgnoreAuditableFields
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(target = "authorId", expression = "java(TimetableUserUtils.getId())")
    Submission asSubmission(String subAssignmentId);

    SubmissionDto asSubmissionDto(Submission submission);

    List<SubmissionDto> asSubmissionDtos(Collection<Submission> submission);

}
