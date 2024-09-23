package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.EducationalProgramResponseDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.domain.EducationalProgram;
import com.pnu.system.academiccatalog.domain.QEducationalProgram;
import com.pnu.system.academiccatalog.domain.QKnowledgeDomain;
import com.pnu.system.academiccatalog.domain.QSpecialty;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationProgramRepository extends JpaRepository<EducationalProgram, String> {

    QEducationalProgram qEducationalProgram = QEducationalProgram.educationalProgram;
    QSpecialty qSpecialty = QSpecialty.specialty;
    QKnowledgeDomain qKnowledgeDomain = QKnowledgeDomain.knowledgeDomain;

    default List<EducationalProgramResponseDto> getAll(BaseSearchRequest searchRequest) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        EducationalProgramResponseDto.class,
                        qEducationalProgram.id,
                        qEducationalProgram.name,
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
                        ),
                        qEducationalProgram.version
                ))
                .from(qEducationalProgram)
                .leftJoin(qEducationalProgram.specialty, qSpecialty)
                .leftJoin(qSpecialty.knowledgeDomain, qKnowledgeDomain)
                .limit(searchRequest.getLimit())
                .offset(searchRequest.getOffset())
                .fetch();
    }
}
