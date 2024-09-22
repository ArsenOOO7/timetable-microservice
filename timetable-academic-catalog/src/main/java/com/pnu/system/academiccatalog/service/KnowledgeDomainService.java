package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.domain.KnowledgeDomain;
import com.pnu.system.academiccatalog.repository.KnowledgeDomainRepository;
import com.pnu.system.common.service.AbstractPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KnowledgeDomainService extends AbstractPersistenceService<KnowledgeDomain> {
    private final KnowledgeDomainRepository repository;

    @Override
    protected Class<KnowledgeDomain> getEntityType() {
        return KnowledgeDomain.class;
    }

    @Override
    protected JpaRepository<KnowledgeDomain, String> getRepository() {
        return repository;
    }
}
