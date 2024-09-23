package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.domain.QKnowledgeDomain;
import com.pnu.system.academiccatalog.domain.QSpecialty;
import com.pnu.system.academiccatalog.domain.Specialty;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, String> {

    QSpecialty qSpecialty = QSpecialty.specialty;
    QKnowledgeDomain qKnowledgeDomain = QKnowledgeDomain.knowledgeDomain;

    default List<SpecialtyResponseDto> getAll(BaseSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        SpecialtyResponseDto.class,
                        qSpecialty.id,
                        qSpecialty.code,
                        qSpecialty.name,
                        qSpecialty.shortName,
                        Projections.constructor(
                                KnowledgeDomainResponseDto.class,
                                qKnowledgeDomain.id,
                                qKnowledgeDomain.code,
                                qKnowledgeDomain.name,
                                qKnowledgeDomain.version
                        ),
                        qSpecialty.version
                ))
                .from(qSpecialty)
                .innerJoin(qSpecialty.knowledgeDomain, qKnowledgeDomain)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }
}
