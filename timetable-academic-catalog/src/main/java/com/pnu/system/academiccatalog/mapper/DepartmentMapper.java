package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.academiccatalog.api.dto.DepartmentCreateDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentResponseDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentUpdateDto;
import com.pnu.system.academiccatalog.domain.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department asDepartment(DepartmentCreateDto createDto);

    Department asDepartment(DepartmentUpdateDto updateDto);

    DepartmentResponseDto asResponseDto(Department department);

}
