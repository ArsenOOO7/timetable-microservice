package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeCreateDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeUpdateDto;
import com.pnu.system.lessonlocation.domain.LocationType;
import com.pnu.system.lessonlocation.domain.QLocationType;
import com.pnu.system.lessonlocation.mapper.LocationTypeMapper;
import com.pnu.system.lessonlocation.repository.LocationTypeRepository;
import com.querydsl.core.types.Projections;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationTypeService extends AbstractPersistenceService<LocationType> {
    private final LocationTypeRepository repository;
    private final LocationTypeMapper mapper;
    private final QLocationType qLocationType = QLocationType.locationType;

    public List<LessonLocationTypeDto> getAll() {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        LessonLocationTypeDto.class,
                        qLocationType.id,
                        qLocationType.name,
                        qLocationType.shortName,
                        qLocationType.version
                ))
                .from(qLocationType)
                .fetch();
    }

    public LessonLocationTypeDto create(LessonLocationTypeCreateDto createDto) {
        return mapper.asLessonLocationTypeDto(create(mapper.asLocationType(createDto)));
    }

    public LessonLocationTypeDto update(LessonLocationTypeUpdateDto updateDto) {
        return mapper.asLessonLocationTypeDto(update(mapper.asLocationType(updateDto)));
    }

    public LessonLocationTypeDto getById(String id) {
        return mapper.asLessonLocationTypeDto(getOne(id));
    }

    @Override
    protected Class<LocationType> getEntityType() {
        return LocationType.class;
    }

    @Override
    protected JpaRepository<LocationType, String> getRepository() {
        return repository;
    }
}
