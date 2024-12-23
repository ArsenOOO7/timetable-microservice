package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainCreateDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainUpdateDto;
import com.pnu.system.academiccatalog.domain.KnowledgeDomain;
import com.pnu.system.academiccatalog.mapper.KnowledgeDomainMapper;
import com.pnu.system.academiccatalog.repository.KnowledgeDomainRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KnowledgeDomainService extends AbstractPersistenceService<KnowledgeDomain> {

    private final KnowledgeDomainMapper mapper;
    private final KnowledgeDomainRepository repository;

    public KnowledgeDomainResponseDto create(@Valid KnowledgeDomainCreateDto createDto) {
        return mapper.asResponseDto(super.create(mapper.asKnowledgeDomain(createDto)));
    }

    public KnowledgeDomainResponseDto update(@Valid KnowledgeDomainUpdateDto updateDto) {
        return mapper.asResponseDto(super.update(mapper.asKnowledgeDomain(updateDto)));
    }

    public KnowledgeDomainResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
    }

    @Override
    protected Class<KnowledgeDomain> getEntityType() {
        return KnowledgeDomain.class;
    }

    @Override
    protected JpaRepository<KnowledgeDomain, String> getRepository() {
        return repository;
    }

}
