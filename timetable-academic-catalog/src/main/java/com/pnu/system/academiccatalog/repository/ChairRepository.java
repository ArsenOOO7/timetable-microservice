package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.ChairPreviewDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentResponseDto;
import com.pnu.system.academiccatalog.domain.Chair;
import com.pnu.system.academiccatalog.domain.QChair;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChairRepository extends JpaRepository<Chair, String> {

    QChair qChair = QChair.chair;

    default List<ChairPreviewDto> getAll(BaseSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        ChairPreviewDto.class,
                        qChair.id,
                        qChair.name,
                        qChair.shortName,
                        Projections.constructor(
                                DepartmentResponseDto.class,
                                qChair.department.id,
                                qChair.department.name,
                                qChair.department.shortName,
                                qChair.department.version
                        )
                ))
                .from(qChair)
                .innerJoin(qChair.department)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }
}
