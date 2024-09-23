package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.EducationalProgramCreateDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramPreviewDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramResponseDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramUpdateDto;
import com.pnu.system.academiccatalog.domain.EducationalProgram;
import com.pnu.system.academiccatalog.mapper.EducationProgramMapper;
import com.pnu.system.academiccatalog.repository.EducationProgramRepository;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationalProgramService extends AbstractPersistenceService<EducationalProgram> {

    private final EducationProgramMapper mapper;
    private final SpecialtyService specialtyService;
    private final EducationProgramRepository repository;

    public List<EducationalProgramPreviewDto> getAll(BaseSearchRequest request) {
        return repository.getAll(request);
    }

    public EducationalProgramResponseDto create(EducationalProgramCreateDto createDto) {
        EducationalProgram educationalProgram = mapper.asEducationalProgram(createDto);
        educationalProgram.setSpecialty(specialtyService.getOne(createDto.getSpecialtyId()));
        return mapper.asResponseDto(super.create(educationalProgram));
    }

    public EducationalProgramResponseDto update(EducationalProgramUpdateDto updateDto) {
        EducationalProgram educationalProgram = mapper.asEducationalProgram(updateDto);
        educationalProgram.setSpecialty(specialtyService.getOne(updateDto.getSpecialtyId()));
        return mapper.asResponseDto(super.update(educationalProgram));
    }

    public EducationalProgramResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
    }

    @Override
    protected Class<EducationalProgram> getEntityType() {
        return EducationalProgram.class;
    }

    @Override
    protected JpaRepository<EducationalProgram, String> getRepository() {
        return repository;
    }
}
