package com.pnu.system.timetable.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.timetable.api.dto.search.BaseLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.GroupLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.LocationLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.TeacherLessonSearchRequest;
import com.pnu.system.timetable.domain.Lesson;
import com.pnu.system.timetable.domain.LessonSearch;
import com.pnu.system.timetable.domain.QLessonSearch;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String> {

    QLessonSearch qLessonSearch = QLessonSearch.lessonSearch;

    default List<LessonSearch> getByGroup(GroupLessonSearchRequest request) {
        return fetch(request, qLessonSearch.groupIds.any().eq(request.getGroupId()));
    }

    default List<LessonSearch> getByGroups(BaseLessonSearchRequest request, List<String> groupIds) {
        return fetch(request, qLessonSearch.groupIds.any().in(groupIds));
    }

    default List<LessonSearch> getByTeacher(TeacherLessonSearchRequest request) {
        return getByTeacher(request, request.getTeacherId());
    }

    default List<LessonSearch> getByTeacher(BaseLessonSearchRequest request, String teacherId) {
        return fetch(request, qLessonSearch.teacherId.eq(teacherId));
    }

    default List<LessonSearch> getByLocation(LocationLessonSearchRequest request) {
        return fetch(request, qLessonSearch.location.id.eq(request.getLocationId()));
    }

    default List<LessonSearch> fetch(BaseLessonSearchRequest request, Predicate... predicates) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qLessonSearch)
                .innerJoin(qLessonSearch.type).fetchJoin()
                .innerJoin(qLessonSearch.teacher).fetchJoin()
                .innerJoin(qLessonSearch.subject).fetchJoin()
                .leftJoin(qLessonSearch.location).fetchJoin()
                .leftJoin(qLessonSearch.groups).fetchJoin()
                .where(qLessonSearch.date.after(request.getFromDate()), qLessonSearch.date.before(request.getToDate()))
                .orderBy(qLessonSearch.date.asc(), qLessonSearch.number.asc())
                .where(predicates)
                .fetch();
    }
}