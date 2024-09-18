package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.lessonlocation.api.dto.LessonLocationCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationUpdateRequest;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import com.pnu.system.lessonlocation.domain.QLessonLocation;
import com.pnu.system.lessonlocation.domain.QLocationType;
import com.pnu.system.lessonlocation.mapper.LessonLocationMapper;
import com.pnu.system.lessonlocation.repository.LessonLocationRepository;
import com.querydsl.core.types.Projections;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonLocationService extends AbstractPersistenceService<LessonLocation> {

    private final LessonLocationRepository repository;
    private final LessonLocationMapper mapper;
    private final LocationTypeService locationTypeService;
    private final QLessonLocation qLessonLocation = QLessonLocation.lessonLocation;
    private final QLocationType qLocationType = QLocationType.locationType;

    public List<LessonLocationDto> getAllLessonLocation() {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        LessonLocationDto.class,
                        qLessonLocation.id,
                        Projections.constructor(LessonLocationTypeDto.class,
                                qLocationType.id,
                                qLocationType.name,
                                qLocationType.shortName,
                                qLocationType.version
                        ),
                        qLessonLocation.name,
                        qLessonLocation.address,
                        qLessonLocation.version
                ))
                .from(qLessonLocation)
                .join(qLessonLocation.locationType, qLocationType)
                .fetch();
    }

    public LessonLocationDto create(LessonLocationCreateRequest createRequest) {
        LessonLocation newLessonLocation = mapper.asLessonLocation(createRequest);
        newLessonLocation.setLocationType(locationTypeService.getOne(createRequest.getLocationTypeId()));
        return mapper.asLessonLocationDto(create(newLessonLocation));
    }

    public LessonLocationDto update(LessonLocationUpdateRequest updateRequest) {
        LessonLocation updatedLessonLocation = mapper.asLessonLocation(updateRequest);
        updatedLessonLocation.setLocationType(locationTypeService.getOne(updateRequest.getLocationTypeId()));
        return mapper.asLessonLocationDto(update(updatedLessonLocation));
    }

    public LessonLocationDto getById(String id) {
        return mapper.asLessonLocationDto(getOne(id));
    }

    @Override
    protected Class<LessonLocation> getEntityType() {
        return LessonLocation.class;
    }

    @Override
    protected JpaRepository<LessonLocation, String> getRepository() {
        return repository;
    }
}
