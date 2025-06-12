package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkDto;
import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkUpdateRequest;
import com.pnu.system.laboratorycontrol.domain.CoursePrimaryLink;
import com.pnu.system.laboratorycontrol.mapper.CoursePrimaryLinkMapper;
import com.pnu.system.laboratorycontrol.repository.CoursePrimaryLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursePrimaryLinkService extends AbstractPersistenceService<CoursePrimaryLink> {

    private final CoursePrimaryLinkMapper mapper;
    private final CoursePrimaryLinkRepository repository;

    public CoursePrimaryLinkDto create(CoursePrimaryLinkCreateRequest request) {
        CoursePrimaryLink primaryLink = mapper.asCoursePrimaryLink(request);
        return mapper.asCoursePrimaryLinkDto(super.create(primaryLink));
    }

    public CoursePrimaryLinkDto update(CoursePrimaryLinkUpdateRequest request) {
        CoursePrimaryLink primaryLink = getOne(request.getId());
        mapper.applyCoursePrimaryLinkUpdateRequest(primaryLink, request);
        return mapper.asCoursePrimaryLinkDto(super.update(primaryLink));
    }

    public List<CoursePrimaryLinkDto> getList(String courseId) {
        return mapper.asCoursePrimaryLinkDtos(repository.findAllByCourseId(courseId));
    }

    @Override
    protected Class<CoursePrimaryLink> getEntityType() {
        return CoursePrimaryLink.class;
    }

    @Override
    protected JpaRepository<CoursePrimaryLink, String> getRepository() {
        return repository;
    }
}
