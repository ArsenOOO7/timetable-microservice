package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.KnowledgeDomainSearch;
import com.pnu.system.academiccatalog.repository.KnowledgeDomainSearchRepository;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KnowledgeDomainSearchService extends AbstractSearchService<KnowledgeDomainSearch> {

    private final KnowledgeDomainSearchRepository repository;

    @Override
    protected AbstractSearchRepository<KnowledgeDomainSearch> getRepository() {
        return repository;
    }
}
