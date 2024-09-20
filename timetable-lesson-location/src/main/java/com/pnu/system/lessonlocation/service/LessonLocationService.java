package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.lessonlocation.api.dto.LessonLocationCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationUpdateRequest;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import com.pnu.system.lessonlocation.mapper.LessonLocationMapper;
import com.pnu.system.lessonlocation.repository.LessonLocationRepository;
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

    public List<LessonLocationResponseDto> getAll() {
        return repository.getAll();
    }

    public LessonLocationResponseDto create(LessonLocationCreateRequest createRequest) {
        LessonLocation newLessonLocation = mapper.asLessonLocation(createRequest);
        newLessonLocation.setLocationType(locationTypeService.getOne(createRequest.getLocationTypeId()));
        return mapper.asLessonLocationDto(create(newLessonLocation));
    }

    public LessonLocationResponseDto update(LessonLocationUpdateRequest updateRequest) {
        LessonLocation updatedLessonLocation = mapper.asLessonLocation(updateRequest);
        updatedLessonLocation.setLocationType(locationTypeService.getOne(updateRequest.getLocationTypeId()));
        return mapper.asLessonLocationDto(update(updatedLessonLocation));
    }

    public LessonLocationResponseDto getById(String id) {
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
