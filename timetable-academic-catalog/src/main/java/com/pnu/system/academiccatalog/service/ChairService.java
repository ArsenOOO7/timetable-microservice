package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.Chair;
import com.pnu.system.academiccatalog.repository.ChairRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChairService extends AbstractPersistenceService<Chair> {
    private final ChairRepository repository;

    @Override
    protected Class<Chair> getEntityType() {
        return Chair.class;
    }

    @Override
    protected JpaRepository<Chair, String> getRepository() {
        return repository;
    }
}
