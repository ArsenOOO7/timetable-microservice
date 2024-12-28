package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.SpecialtyCreateDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyUpdateDto;
import com.pnu.system.academiccatalog.domain.Specialty;
import com.pnu.system.academiccatalog.mapper.SpecialtyMapper;
import com.pnu.system.academiccatalog.repository.SpecialtyRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyService extends AbstractPersistenceService<Specialty> {

    private final SpecialtyMapper mapper;
    private final SpecialtyRepository repository;

    public SpecialtyResponseDto create(SpecialtyCreateDto createDto) {
        Specialty specialty = mapper.asSpecialty(createDto);
        return mapper.asResponseDto(super.create(specialty));
    }

    public SpecialtyResponseDto update(SpecialtyUpdateDto updateDto) {
        Specialty specialty = mapper.asSpecialty(updateDto);
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
