package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.domain.KnowledgeDomain;
import com.pnu.system.academiccatalog.domain.QKnowledgeDomain;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeDomainRepository extends JpaRepository<KnowledgeDomain, String> {

    QKnowledgeDomain qKnowledgeDomain = QKnowledgeDomain.knowledgeDomain;

    default List<KnowledgeDomainResponseDto> getAll(BaseSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        KnowledgeDomainResponseDto.class,
                        qKnowledgeDomain.id,
                        qKnowledgeDomain.code,
                        qKnowledgeDomain.name,
                        qKnowledgeDomain.version
                ))
                .from(qKnowledgeDomain)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }

}
