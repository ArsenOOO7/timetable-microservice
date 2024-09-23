package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.EducationalProgramPreviewDto;
import com.pnu.system.academiccatalog.api.dto.SubjectPreviewDto;
import com.pnu.system.academiccatalog.domain.QSubject;
import com.pnu.system.academiccatalog.domain.Subject;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {

    QSubject qSubject = QSubject.subject;

    default List<SubjectPreviewDto> getAll(BaseSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        SubjectPreviewDto.class,
                        qSubject.id,
                        qSubject.name,
                        Projections.constructor(
                                EducationalProgramPreviewDto.class,
                                qSubject.educationalProgram.id,
                                qSubject.educationalProgram.name
                        )
                ))
                .from(qSubject)
                .innerJoin(qSubject.educationalProgram)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }
}
