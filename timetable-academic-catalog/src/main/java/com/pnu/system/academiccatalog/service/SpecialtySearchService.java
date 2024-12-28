package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.SpecialtySearch;
import com.pnu.system.academiccatalog.repository.SpecialtySearchRepository;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpecialtySearchService extends AbstractSearchService<SpecialtySearch> {

    private final SpecialtySearchRepository repository;

    @Override
    protected AbstractSearchRepository<SpecialtySearch> getRepository() {
        return repository;
    }
}
