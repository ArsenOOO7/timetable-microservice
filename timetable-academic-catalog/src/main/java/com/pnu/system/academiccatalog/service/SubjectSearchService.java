package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.SubjectSearch;
import com.pnu.system.academiccatalog.repository.SubjectSearchRepository;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubjectSearchService extends AbstractSearchService<SubjectSearch> {

    private final SubjectSearchRepository repository;

    @Override
    protected AbstractSearchRepository<SubjectSearch> getRepository() {
        return repository;
    }
}
