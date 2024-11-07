package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.SubjectCreateDto;
import com.pnu.system.academiccatalog.api.dto.SubjectPreviewDto;
import com.pnu.system.academiccatalog.api.dto.SubjectResponseDto;
import com.pnu.system.academiccatalog.api.dto.SubjectUpdateDto;
import com.pnu.system.academiccatalog.domain.Subject;
import com.pnu.system.academiccatalog.event.model.SubjectCreateEvent;
import com.pnu.system.academiccatalog.event.model.SubjectDeleteEvent;
import com.pnu.system.academiccatalog.event.model.SubjectUpdateEvent;
import com.pnu.system.academiccatalog.mapper.SubjectMapper;
import com.pnu.system.academiccatalog.repository.SubjectRepository;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectService extends AbstractPersistenceService<Subject> {

    private final SubjectMapper mapper;
    private final SubjectRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final EducationalProgramService educationalProgramService;

    public List<SubjectPreviewDto> getAll(BaseSearchRequest request) {
        return repository.getAll(request);
    }

    public SubjectResponseDto create(@Valid SubjectCreateDto createDto) {
        Subject subject = mapper.asSubject(createDto);
        subject.setEducationalProgram(educationalProgramService.getOne(createDto.getEducationalProgramId()));
        eventPublisher.publishEvent(new SubjectCreateEvent(subject));
        return mapper.asResponseDto(super.create(subject));
    }

    public SubjectResponseDto update(@Valid SubjectUpdateDto updateDto) {
        Subject subject = mapper.asSubject(updateDto);
        subject.setEducationalProgram(educationalProgramService.getOne(updateDto.getEducationalProgramId()));
        eventPublisher.publishEvent(new SubjectUpdateEvent(subject));
        return mapper.asResponseDto(super.update(subject));
    }

    public SubjectResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
    }

    @Override
    public void delete(Subject entity) {
        eventPublisher.publishEvent(new SubjectDeleteEvent(entity.getId()));
        super.delete(entity);
    }

    @Override
    protected Class<Subject> getEntityType() {
        return Subject.class;
    }

    @Override
    protected JpaRepository<Subject, String> getRepository() {
        return repository;
    }
}
