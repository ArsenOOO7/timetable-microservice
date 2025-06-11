package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.laboratorycontrol.domain.Course;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlGroupSnapshot;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlUserSnapshot;
import com.pnu.system.laboratorycontrol.domain.QCourseSearch;
import com.pnu.system.laboratorycontrol.domain.QLaboratoryControlGroupSnapshot;
import com.pnu.system.laboratorycontrol.domain.QLaboratoryControlUserSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    QCourseSearch qCourseSearch = QCourseSearch.courseSearch;
    QLaboratoryControlUserSnapshot qUser = QLaboratoryControlUserSnapshot.laboratoryControlUserSnapshot;
    QLaboratoryControlGroupSnapshot qGroup = QLaboratoryControlGroupSnapshot.laboratoryControlGroupSnapshot;

    default List<LaboratoryControlUserSnapshot> getAuthors(String id) {
        return QueryDslFactory.getQueryFactory()
                .select(qUser)
                .from(qCourseSearch)
                .innerJoin(qCourseSearch.authors, qUser)
                .where(qCourseSearch.id.eq(id))
                .fetch();
    }

    default List<LaboratoryControlGroupSnapshot> getGroups(String id) {
        return QueryDslFactory.getQueryFactory()
                .select(qGroup)
                .from(qCourseSearch)
                .innerJoin(qCourseSearch.groups, qGroup)
                .where(qCourseSearch.id.eq(id))
                .fetch();
    }
}
