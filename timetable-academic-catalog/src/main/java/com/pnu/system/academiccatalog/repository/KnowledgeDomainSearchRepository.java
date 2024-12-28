package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.KnowledgeDomainSearch;
import com.pnu.system.academiccatalog.domain.QKnowledgeDomainSearch;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class KnowledgeDomainSearchRepository extends AbstractSearchRepository<KnowledgeDomainSearch> {
    @Override
    protected EntityPathBase<KnowledgeDomainSearch> getPath() {
        return QKnowledgeDomainSearch.knowledgeDomainSearch;
    }
}
