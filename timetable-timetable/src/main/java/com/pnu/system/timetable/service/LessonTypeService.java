package com.pnu.system.timetable.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.timetable.api.dto.LessonTypeCreateRequest;
import com.pnu.system.timetable.api.dto.LessonTypeResponseDto;
import com.pnu.system.timetable.api.dto.LessonTypeUpdateRequest;
import com.pnu.system.timetable.domain.LessonType;
import com.pnu.system.timetable.mapper.LessonTypeMapper;
import com.pnu.system.timetable.repository.LessonTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonTypeService extends AbstractPersistenceService<LessonType> {

    private final LessonTypeMapper mapper;
    private final LessonTypeRepository repository;

    public LessonTypeResponseDto create(LessonTypeCreateRequest request) {
        LessonType type = mapper.asLessonType(request);
        return mapper.asLessonTypeResponseDto(super.create(type));
    }

    public LessonTypeResponseDto update(LessonTypeUpdateRequest request) {
        LessonType type = mapper.asLessonType(request);
        return mapper.asLessonTypeResponseDto(super.update(type));
    }

    public List<LessonTypeResponseDto> getList() {
        return mapper.asLessonTypeResponseDtos(repository.findAll());
    }

    @Override
    protected Class<LessonType> getEntityType() {
        return LessonType.class;
    }

    @Override
    protected JpaRepository<LessonType, String> getRepository() {
        return repository;
    }
}
