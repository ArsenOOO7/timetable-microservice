package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.Specialty;
import com.pnu.system.academiccatalog.repository.SpecialtyRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyService extends AbstractPersistenceService<Specialty> {
    private final SpecialtyRepository repository;

    @Override
    protected Class<Specialty> getEntityType() {
        return Specialty.class;
    }

    @Override
    protected JpaRepository<Specialty, String> getRepository() {
        return repository;
    }
}
