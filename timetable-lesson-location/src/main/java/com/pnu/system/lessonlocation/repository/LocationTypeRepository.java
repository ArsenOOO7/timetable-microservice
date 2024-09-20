package com.pnu.system.lessonlocation.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeResponseDto;
import com.pnu.system.lessonlocation.domain.LocationType;
import com.pnu.system.lessonlocation.domain.QLocationType;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationTypeRepository extends JpaRepository<LocationType, String> {
    QLocationType qLocationType = QLocationType.locationType;

    default List<LessonLocationTypeResponseDto> getAll() {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        LessonLocationTypeResponseDto.class,
                        qLocationType.id,
                        qLocationType.name,
                        qLocationType.shortName,
                        qLocationType.version
                ))
                .from(qLocationType)
                .fetch();
    }
}
