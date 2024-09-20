package com.pnu.system.lessonlocation.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.lessonlocation.api.dto.LessonLocationResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeResponseDto;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import com.pnu.system.lessonlocation.domain.QLessonLocation;
import com.pnu.system.lessonlocation.domain.QLocationType;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonLocationRepository extends JpaRepository<LessonLocation, String> {
    QLessonLocation qLessonLocation = QLessonLocation.lessonLocation;
    QLocationType qLocationType = QLocationType.locationType;

    default List<LessonLocationResponseDto> getAll() {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        LessonLocationResponseDto.class,
                        qLessonLocation.id,
                        Projections.constructor(LessonLocationTypeResponseDto.class,
                                qLocationType.id,
                                qLocationType.name,
                                qLocationType.shortName,
                                qLocationType.version
                        ),
                        qLessonLocation.name,
                        qLessonLocation.address,
                        qLessonLocation.version
                ))
                .from(qLessonLocation)
                .join(qLessonLocation.locationType, qLocationType)
                .fetch();
    }
}
