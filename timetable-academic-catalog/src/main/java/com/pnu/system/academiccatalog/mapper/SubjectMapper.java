package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.academiccatalog.api.dto.SubjectCreateDto;
import com.pnu.system.academiccatalog.api.dto.SubjectResponseDto;
import com.pnu.system.academiccatalog.api.dto.SubjectUpdateDto;
import com.pnu.system.academiccatalog.domain.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EducationProgramMapper.class})
public interface SubjectMapper {

    Subject asSubject(SubjectCreateDto subjectCreateDto);

    Subject asSubject(SubjectUpdateDto subjectUpdateDto);

    SubjectResponseDto asResponseDto(Subject subject);

}
