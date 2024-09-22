package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.Department;
import com.pnu.system.academiccatalog.repository.DepartmentRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService extends AbstractPersistenceService<Department> {
    private final DepartmentRepository repository;

    @Override
    protected Class<Department> getEntityType() {
        return Department.class;
    }

    @Override
    protected JpaRepository<Department, String> getRepository() {
        return repository;
    }
}
