package com.pnu.system.identityaccess.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.identityaccess.domain.AcademicStatus;
import com.pnu.system.identityaccess.repository.AcademicStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicStatusService extends AbstractPersistenceService<AcademicStatus> {

    private final AcademicStatusRepository repository;

    public List<AcademicStatus> getAll() {
        return repository.findAll();
    }

    @Override
    protected Class<AcademicStatus> getEntityType() {
        return AcademicStatus.class;
    }

    @Override
    protected JpaRepository<AcademicStatus, String> getRepository() {
        return repository;
    }
}
