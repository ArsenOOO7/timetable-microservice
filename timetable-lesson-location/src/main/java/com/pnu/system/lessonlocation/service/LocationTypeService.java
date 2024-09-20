package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeCreateDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeUpdateDto;
import com.pnu.system.lessonlocation.domain.LocationType;
import com.pnu.system.lessonlocation.mapper.LocationTypeMapper;
import com.pnu.system.lessonlocation.repository.LocationTypeRepository;
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

    public List<LessonLocationTypeResponseDto> getAll() {
        return repository.getAll();
    }

    public LessonLocationTypeResponseDto create(LessonLocationTypeCreateDto createDto) {
        return mapper.asLessonLocationTypeDto(create(mapper.asLocationType(createDto)));
    }

    public LessonLocationTypeResponseDto update(LessonLocationTypeUpdateDto updateDto) {
        return mapper.asLessonLocationTypeDto(update(mapper.asLocationType(updateDto)));
    }

    public LessonLocationTypeResponseDto getById(String id) {
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
