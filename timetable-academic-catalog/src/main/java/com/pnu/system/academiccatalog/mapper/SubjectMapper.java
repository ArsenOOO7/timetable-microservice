package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.academiccatalog.api.dto.ChairCreateDto;
import com.pnu.system.academiccatalog.api.dto.ChairResponseDto;
import com.pnu.system.academiccatalog.api.dto.ChairUpdateDto;
import com.pnu.system.academiccatalog.domain.Chair;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, SpecialtyMapper.class})
public interface SubjectMapper {

    Chair asChair(ChairCreateDto chairCreateDto);

    Chair asChair(ChairUpdateDto chairUpdateDto);

    ChairResponseDto asResponseDto(Chair chair);

}
