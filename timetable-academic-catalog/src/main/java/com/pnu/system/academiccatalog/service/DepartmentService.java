package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.DepartmentCreateDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentResponseDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentUpdateDto;
import com.pnu.system.academiccatalog.domain.Department;
import com.pnu.system.academiccatalog.mapper.DepartmentMapper;
import com.pnu.system.academiccatalog.repository.DepartmentRepository;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService extends AbstractPersistenceService<Department> {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public List<DepartmentResponseDto> getAll(BaseSearchRequest searchRequest) {
        return repository.getAll(searchRequest);
    }

    public DepartmentResponseDto create(@Valid DepartmentCreateDto createDto) {
        return mapper.asResponseDto(super.create(mapper.asDepartment(createDto)));
    }

    public DepartmentResponseDto update(@Valid DepartmentUpdateDto updateDto) {
        return mapper.asResponseDto(super.update(mapper.asDepartment(updateDto)));
    }

    public DepartmentResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
    }

    public void delete(String id) {
        super.delete(id);
    }

    @Override
    protected Class<Department> getEntityType() {
        return Department.class;
    }

    @Override
    protected JpaRepository<Department, String> getRepository() {
        return repository;
    }
}
