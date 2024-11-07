package com.pnu.system.lessonlocation.repository;

import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import com.pnu.system.lessonlocation.domain.QLessonLocation;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonLocationSnapshotRepository extends JpaRepository<LessonLocation, String> {

    QLessonLocation qLessonLocation = QLessonLocation.lessonLocation;

    default LessonLocationSnapshotDto getSnapshotById(String id) {
        return QueryDslFactory.getQueryFactory()
                .select(getLessonLocationSnapshotProjection())
                .from(qLessonLocation)
                .innerJoin(qLessonLocation.locationType)
                .where(qLessonLocation.id.eq(id))
                .fetchOne();
    }

    default List<LessonLocationSnapshotDto> getByIds(List<String> ids) {
        return QueryDslFactory.getQueryFactory()
                .select(getLessonLocationSnapshotProjection())
                .from(qLessonLocation)
                .innerJoin(qLessonLocation.locationType)
                .where(qLessonLocation.id.in(ids))
                .fetch();
    }

    default List<LessonLocationSnapshotDto> getByLocationTypeId(String locationTypeId) {
        return QueryDslFactory.getQueryFactory()
                .select(getLessonLocationSnapshotProjection())
                .from(qLessonLocation)
                .innerJoin(qLessonLocation.locationType)
                .where(qLessonLocation.locationType.id.eq(locationTypeId))
                .fetch();
    }

    default ConstructorExpression<LessonLocationSnapshotDto> getLessonLocationSnapshotProjection() {
        return Projections.constructor(LessonLocationSnapshotDto.class, qLessonLocation.id, qLessonLocation.locationType.shortName,
                qLessonLocation.name, qLessonLocation.address);
    }
}
