package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.EducationalProgramSearch;
import com.pnu.system.academiccatalog.repository.EducationProgramSearchRepository;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EducationalProgramSearchService extends AbstractSearchService<EducationalProgramSearch> {

    private final EducationProgramSearchRepository repository;

    @Override
    protected AbstractSearchRepository<EducationalProgramSearch> getRepository() {
        return repository;
    }
}
