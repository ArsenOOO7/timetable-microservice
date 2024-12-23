package com.pnu.system.lessonlocation.repository;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import com.pnu.system.lessonlocation.domain.QLessonLocation;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class LocationSearchRepository extends AbstractSearchRepository<LessonLocation> {
    @Override
    protected EntityPathBase<LessonLocation> getPath() {
        return QLessonLocation.lessonLocation;
    }
}
