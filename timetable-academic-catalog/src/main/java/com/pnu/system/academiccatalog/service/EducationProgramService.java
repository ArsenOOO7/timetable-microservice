package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.EducationalProgram;
import com.pnu.system.academiccatalog.repository.EducationProgramRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationProgramService extends AbstractPersistenceService<EducationalProgram> {
    private final EducationProgramRepository repository;

    @Override
    protected Class<EducationalProgram> getEntityType() {
        return EducationalProgram.class;
    }

    @Override
    protected JpaRepository<EducationalProgram, String> getRepository() {
        return repository;
    }
}
