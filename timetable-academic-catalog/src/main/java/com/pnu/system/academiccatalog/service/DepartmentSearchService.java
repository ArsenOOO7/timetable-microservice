package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.DepartmentSearch;
import com.pnu.system.academiccatalog.repository.DepartmentSearchRepository;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentSearchService extends AbstractSearchService<DepartmentSearch> {

    private final DepartmentSearchRepository repository;

    @Override
    protected AbstractSearchRepository<DepartmentSearch> getRepository() {
        return repository;
    }
}
