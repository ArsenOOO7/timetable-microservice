package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.api.dto.ChairCreateDto;
import com.pnu.system.academiccatalog.api.dto.ChairResponseDto;
import com.pnu.system.academiccatalog.api.dto.ChairUpdateDto;
import com.pnu.system.academiccatalog.domain.Chair;
import com.pnu.system.academiccatalog.mapper.ChairMapper;
import com.pnu.system.academiccatalog.repository.ChairRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChairService extends AbstractPersistenceService<Chair> {

    private final ChairMapper mapper;
    private final ChairRepository repository;

    public ChairResponseDto create(ChairCreateDto createDto) {
        Chair chair = mapper.asChair(createDto);
        return mapper.asResponseDto(super.create(chair));
    }

    public ChairResponseDto update(ChairUpdateDto updateDto) {
        Chair chair = mapper.asChair(updateDto);
        return mapper.asResponseDto(super.update(chair));
    }

    public ChairResponseDto getById(String id) {
        return mapper.asResponseDto(super.getOne(id));
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
