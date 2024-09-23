package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.academiccatalog.api.dto.SpecialtyCreateDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyUpdateDto;
import com.pnu.system.academiccatalog.domain.Specialty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    Specialty asSpecialty(SpecialtyCreateDto createDto);

    Specialty asSpecialty(SpecialtyUpdateDto updateDto);

    SpecialtyResponseDto asResponseDto(Specialty specialty);

}
