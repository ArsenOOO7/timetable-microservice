package com.pnu.system.academiccatalog.mapper;

import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainCreateDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainUpdateDto;
import com.pnu.system.academiccatalog.domain.KnowledgeDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KnowledgeDomainMapper {

    KnowledgeDomain asKnowledgeDomain(KnowledgeDomainCreateDto createDto);

    KnowledgeDomain asKnowledgeDomain(KnowledgeDomainUpdateDto updateDto);

    KnowledgeDomainResponseDto asResponseDto(KnowledgeDomain domain);
}
