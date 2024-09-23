package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.ChairResponseDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentResponseDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.domain.Chair;
import com.pnu.system.academiccatalog.domain.QChair;
import com.pnu.system.academiccatalog.domain.QDepartment;
import com.pnu.system.academiccatalog.domain.QKnowledgeDomain;
import com.pnu.system.academiccatalog.domain.QSpecialty;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChairRepository extends JpaRepository<Chair, String> {

    QChair qChair = QChair.chair;
    QDepartment qDepartment = QDepartment.department;
    QSpecialty qSpecialty = QSpecialty.specialty;
    QKnowledgeDomain qKnowledgeDomain = QKnowledgeDomain.knowledgeDomain;

    default List<ChairResponseDto> getAll(BaseSearchRequest searchRequest) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        ChairResponseDto.class,
                        qChair.id,
                        qChair.name,
                        qChair.shortName,
                        Projections.constructor(
                                DepartmentResponseDto.class,
                                qDepartment.id,
                                qDepartment.name,
                                qDepartment.shortName,
                                qDepartment.version
                        ),
                        Projections.list(
                                Projections.constructor(
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
                                )
                        ),
                        qChair.version
                ))
                .from(qChair)
                .leftJoin(qChair.specialties, qSpecialty)
                .leftJoin(qChair.department, qDepartment)
                .leftJoin(qSpecialty.knowledgeDomain, qKnowledgeDomain)
                .limit(searchRequest.getLimit())
                .offset(searchRequest.getOffset())
                .fetch();
    }

}
