package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.Subject;
import com.pnu.system.academiccatalog.repository.SubjectRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubjectService extends AbstractPersistenceService<Subject> {
    private final SubjectRepository repository;

    @Override
    protected Class<Subject> getEntityType() {
        return Subject.class;
    }

    @Override
    protected JpaRepository<Subject, String> getRepository() {
        return repository;
    }
}
