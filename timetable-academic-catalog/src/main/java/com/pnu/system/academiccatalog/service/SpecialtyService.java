package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.SpecialtyCreateDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyUpdateDto;
import com.pnu.system.academiccatalog.domain.Specialty;
import com.pnu.system.academiccatalog.mapper.SpecialtyMapper;
import com.pnu.system.academiccatalog.repository.SpecialtyRepository;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyService extends AbstractPersistenceService<Specialty> {
    private final SpecialtyRepository repository;
    private final SpecialtyMapper mapper;
    private final KnowledgeDomainService knowledgeDomainService;

    public List<SpecialtyResponseDto> getAll(BaseSearchRequest request) {
        return repository.getAll(request);
    }

    public SpecialtyResponseDto create(@Valid SpecialtyCreateDto createDto) {
        Specialty specialty = mapper.asSpecialty(createDto);
        specialty.setKnowledgeDomain(knowledgeDomainService.getOne(createDto.getKnowledgeDomainId()));
        return mapper.asResponseDto(super.create(specialty));
    }

    public SpecialtyResponseDto update(@Valid SpecialtyUpdateDto updateDto) {
        Specialty specialty = mapper.asSpecialty(updateDto);
        specialty.setKnowledgeDomain(knowledgeDomainService.getOne(updateDto.getKnowledgeDomainId()));
        return mapper.asResponseDto(super.update(specialty));
    }

    public SpecialtyResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
    }

    @Override
    protected Class<Specialty> getEntityType() {
        return Specialty.class;
    }

    @Override
    protected JpaRepository<Specialty, String> getRepository() {
        return repository;
    }
}
