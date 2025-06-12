package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkDto;
import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkUpdateRequest;
import com.pnu.system.laboratorycontrol.domain.CoursePrimaryLink;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursePrimaryLinkMapper {

    CoursePrimaryLinkDto asCoursePrimaryLinkDto(CoursePrimaryLink primaryLink);

    List<CoursePrimaryLinkDto> asCoursePrimaryLinkDtos(Collection<CoursePrimaryLink> primaryLinks);

    CoursePrimaryLink asCoursePrimaryLink(CoursePrimaryLinkCreateRequest request);

    void applyCoursePrimaryLinkUpdateRequest(@MappingTarget CoursePrimaryLink primaryLink, CoursePrimaryLinkUpdateRequest request);

}
