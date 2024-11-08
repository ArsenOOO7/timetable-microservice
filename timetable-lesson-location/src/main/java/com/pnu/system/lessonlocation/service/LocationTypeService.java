package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.exception.ValidationException;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeUpdateDto;
import com.pnu.system.lessonlocation.domain.LocationType;
import com.pnu.system.lessonlocation.event.model.LessonLocationTypeUpdateEvent;
import com.pnu.system.lessonlocation.mapper.LocationTypeMapper;
import com.pnu.system.lessonlocation.repository.LessonLocationRepository;
import com.pnu.system.lessonlocation.repository.LocationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationTypeService extends AbstractPersistenceService<LocationType> {

    private final LocationTypeMapper mapper;
    private final LocationTypeRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final LessonLocationRepository lessonLocationRepository;

    public List<LessonLocationTypeResponseDto> getAll() {
        return repository.getAll();
    }

    public LessonLocationTypeResponseDto create(LessonLocationTypeCreateRequest createDto) {
        return mapper.asLessonLocationTypeDto(super.create(mapper.asLocationType(createDto)));
    }

    public LessonLocationTypeResponseDto update(LessonLocationTypeUpdateDto updateDto) {
        LocationType locationType = mapper.asLocationType(updateDto);
        eventPublisher.publishEvent(new LessonLocationTypeUpdateEvent(locationType));
        return mapper.asLessonLocationTypeDto(super.update(locationType));
    }

    public LessonLocationTypeResponseDto getById(String id) {
        return mapper.asLessonLocationTypeDto(getOne(id));
    }

    @Override
    public void delete(LocationType entity) {
        if (lessonLocationRepository.existsByLocationTypeId(entity.getId())) {
            throw new ValidationException("Lesson Location Type is used in Lesson Location.");
        }
        super.delete(entity);
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
