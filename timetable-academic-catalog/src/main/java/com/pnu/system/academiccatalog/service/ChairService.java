package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.ChairCreateDto;
import com.pnu.system.academiccatalog.api.dto.ChairPreviewDto;
import com.pnu.system.academiccatalog.api.dto.ChairResponseDto;
import com.pnu.system.academiccatalog.api.dto.ChairUpdateDto;
import com.pnu.system.academiccatalog.domain.Chair;
import com.pnu.system.academiccatalog.mapper.ChairMapper;
import com.pnu.system.academiccatalog.repository.ChairRepository;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChairService extends AbstractPersistenceService<Chair> {

    private final ChairMapper mapper;
    private final ChairRepository repository;
    private final SpecialtyService specialtyService;
    private final DepartmentService departmentService;

    public List<ChairPreviewDto> getAll(BaseSearchRequest request) {
        return repository.getAll(request);
    }

    public ChairResponseDto create(ChairCreateDto createDto) {
        Chair chair = mapper.asChair(createDto);
        chair.setDepartment(departmentService.getOne(createDto.getDepartmentId()));
        assignSpecialties(chair, createDto.getSpecialtiesIds());
        return mapper.asResponseDto(super.create(chair));
    }

    public ChairResponseDto update(ChairUpdateDto updateDto) {
        Chair chair = mapper.asChair(updateDto);
        chair.setDepartment(departmentService.getOne(updateDto.getDepartmentId()));
        assignSpecialties(chair, updateDto.getSpecialtiesIds());
        return mapper.asResponseDto(super.update(chair));
    }

    public ChairResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
    }

    private void assignSpecialties(Chair chair, List<String> specialtyIds) {
        if (CollectionUtils.isEmpty(specialtyIds)) {
            return;
        }
        chair.setSpecialties(specialtyService.getAll(specialtyIds));
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
