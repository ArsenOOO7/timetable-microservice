package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.ChairSearch;
import com.pnu.system.academiccatalog.repository.ChairSearchRepository;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChairSearchService extends AbstractSearchService<ChairSearch> {

    private final ChairSearchRepository repository;

    @Override
    protected AbstractSearchRepository<ChairSearch> getRepository() {
        return repository;
    }
}
