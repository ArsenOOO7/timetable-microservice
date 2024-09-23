package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.EducationalProgramPreviewDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyPreviewDto;
import com.pnu.system.academiccatalog.domain.EducationalProgram;
import com.pnu.system.academiccatalog.domain.QEducationalProgram;
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

    default List<EducationalProgramPreviewDto> getAll(BaseSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        EducationalProgramPreviewDto.class,
                        qEducationalProgram.id,
                        qEducationalProgram.name,
                        Projections.constructor(
                                SpecialtyPreviewDto.class,
                                qSpecialty.id,
                                qSpecialty.shortName
                        )
                ))
                .from(qEducationalProgram)
                .innerJoin(qEducationalProgram.specialty, qSpecialty)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }
}
