package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.lessonlocation.api.dto.LessonLocationCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationUpdateRequest;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import com.pnu.system.lessonlocation.event.model.LessonLocationCreateEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationDeleteEvent;
import com.pnu.system.lessonlocation.event.model.LessonLocationUpdateEvent;
import com.pnu.system.lessonlocation.mapper.LessonLocationMapper;
import com.pnu.system.lessonlocation.repository.LessonLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonLocationService extends AbstractPersistenceService<LessonLocation> {

    private final LessonLocationMapper mapper;
    private final LessonLocationRepository repository;
    private final LocationTypeService locationTypeService;
    private final ApplicationEventPublisher eventPublisher;

    public List<LessonLocationResponseDto> getAll(BaseSearchRequest request) {
        return repository.getAll(request);
    }

    public LessonLocationResponseDto create(LessonLocationCreateRequest createRequest) {
        LessonLocation lessonLocation = mapper.asLessonLocation(createRequest);
        lessonLocation.setLocationType(locationTypeService.getOne(createRequest.getLocationTypeId()));
        eventPublisher.publishEvent(new LessonLocationCreateEvent(lessonLocation));
        return mapper.asLessonLocationDto(super.create(lessonLocation));
    }

    public LessonLocationResponseDto update(LessonLocationUpdateRequest updateRequest) {
        LessonLocation lessonLocation = mapper.asLessonLocation(updateRequest);
        lessonLocation.setLocationType(locationTypeService.getOne(updateRequest.getLocationTypeId()));
        eventPublisher.publishEvent(new LessonLocationUpdateEvent(lessonLocation));
        return mapper.asLessonLocationDto(super.update(lessonLocation));
    }

    public LessonLocationResponseDto getById(String id) {
        return mapper.asLessonLocationDto(getOne(id));
    }

    @Override
    public void delete(LessonLocation entity) {
        eventPublisher.publishEvent(new LessonLocationDeleteEvent(entity.getId()));
        super.delete(entity);
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
