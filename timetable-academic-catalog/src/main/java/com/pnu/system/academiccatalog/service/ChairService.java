package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.ChairCreateDto;
import com.pnu.system.academiccatalog.api.dto.ChairResponseDto;
import com.pnu.system.academiccatalog.api.dto.ChairUpdateDto;
import com.pnu.system.academiccatalog.domain.Chair;
import com.pnu.system.academiccatalog.mapper.ChairMapper;
import com.pnu.system.academiccatalog.repository.ChairRepository;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChairService extends AbstractPersistenceService<Chair> {
    private final ChairRepository repository;
    private final ChairMapper mapper;
    private final DepartmentService departmentService;
    private final SpecialtyService specialtyService;

    public List<ChairResponseDto> getAll(BaseSearchRequest searchRequest) {
        return repository.getAll(searchRequest);
    }

    public ChairResponseDto create(ChairCreateDto createDto) {
        Chair chair = mapper.asChair(createDto);
        chair.setDepartment(departmentService.getOne(createDto.getDepartmentId()));
        chair.setSpecialties(specialtyService.getAll(createDto.getSpecialtiesIds()));
        return mapper.asResponseDto(super.create(chair));
    }

    public ChairResponseDto update(ChairUpdateDto updateDto) {
        Chair chair = mapper.asChair(updateDto);
        chair.setDepartment(departmentService.getOne(updateDto.getDepartmentId()));
        chair.setSpecialties(specialtyService.getAll(updateDto.getSpecialtiesIds()));
        return mapper.asResponseDto(super.update(chair));
    }

    public ChairResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
    }

    public void delete(String id) {
        repository.delete(super.getOne(id));
    }

    @Override
    protected Class<Chair> getEntityType() {
        return Chair.class;
    }

    @Override
    protected JpaRepository<Chair, String> getRepository() {
        return repository;
    }
}
