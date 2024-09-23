package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.academiccatalog.api.dto.EducationalProgramCreateDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramResponseDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramUpdateDto;
import com.pnu.system.academiccatalog.domain.EducationalProgram;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SpecialtyMapper.class})
public interface EducationProgramMapper {

    EducationalProgram asEducationalProgram(EducationalProgramCreateDto createDto);

    EducationalProgram asEducationalProgram(EducationalProgramUpdateDto updateDto);

    EducationalProgramResponseDto asResponseDto(EducationalProgram educationalProgram);
}
