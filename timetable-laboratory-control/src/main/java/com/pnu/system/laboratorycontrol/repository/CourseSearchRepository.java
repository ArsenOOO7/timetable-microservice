package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.laboratorycontrol.domain.CourseSearch;
import com.pnu.system.laboratorycontrol.domain.QCourseSearch;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class CourseSearchRepository extends AbstractSearchRepository<CourseSearch> {

    @Override
    protected EntityPathBase<CourseSearch> getPath() {
        return QCourseSearch.courseSearch;
    }
}
