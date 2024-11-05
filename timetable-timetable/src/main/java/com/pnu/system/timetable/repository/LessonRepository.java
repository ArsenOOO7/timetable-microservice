package com.pnu.system.timetable.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.timetable.api.dto.board.LessonBoardGroupDto;
import com.pnu.system.timetable.api.dto.board.LessonBoardLessonDto;
import com.pnu.system.timetable.api.dto.board.LessonBoardLocationDto;
import com.pnu.system.timetable.api.dto.board.LessonBoardSubjectDto;
import com.pnu.system.timetable.api.dto.board.LessonBoardTypeDto;
import com.pnu.system.timetable.api.dto.board.LessonBoardUserDto;
import com.pnu.system.timetable.api.dto.search.BaseLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.GroupLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.LocationLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.TeacherLessonSearchRequest;
import com.pnu.system.timetable.domain.Lesson;
import com.pnu.system.timetable.domain.LessonSearch;
import com.pnu.system.timetable.domain.QLessonSearch;
import com.pnu.system.timetable.domain.QTimetableGroupSnapshot;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String> {

    QLessonSearch qLessonSearch = QLessonSearch.lessonSearch;
    QTimetableGroupSnapshot qGroup = QTimetableGroupSnapshot.timetableGroupSnapshot;

    default Map<LocalDate, List<LessonBoardLessonDto>> getByGroup(GroupLessonSearchRequest request) {
        return transform(buildQuery(request, qLessonSearch.groupIds.any().eq(request.getGroupId())));
    }

    default Map<LocalDate, List<LessonBoardLessonDto>> getByGroups(BaseLessonSearchRequest request, List<String> groupIds) {
        return transform(buildQuery(request, qLessonSearch.groupIds.any().in(groupIds)));
    }

    default Map<LocalDate, List<LessonBoardLessonDto>> getByTeacher(TeacherLessonSearchRequest request) {
        return getByTeacher(request, request.getTeacherId());
    }

    default Map<LocalDate, List<LessonBoardLessonDto>> getByTeacher(BaseLessonSearchRequest request, String teacherId) {
        return transform(buildQuery(request, qLessonSearch.teacherId.eq(teacherId)));
    }

    default Map<LocalDate, List<LessonBoardLessonDto>> getByLocation(LocationLessonSearchRequest request) {
        return transform(buildQuery(request, qLessonSearch.location.id.eq(request.getLocationId())));
    }

    default JPAQuery<LessonSearch> buildQuery(BaseLessonSearchRequest request, Predicate... predicates) {
        JPAQuery<LessonSearch> query = QueryDslFactory.getQueryFactory()
                .selectFrom(qLessonSearch)
                .innerJoin(qLessonSearch.type)
                .innerJoin(qLessonSearch.teacher)
                .innerJoin(qLessonSearch.subject)
                .leftJoin(qLessonSearch.location)
                .innerJoin(qLessonSearch.groups, qGroup)
                .where(qLessonSearch.date.after(request.getFromDate()), qLessonSearch.date.before(request.getToDate()))
                .orderBy(qLessonSearch.date.asc(), qLessonSearch.number.asc());
        query.where(predicates);
        return query;
    }

    default Map<LocalDate, List<LessonBoardLessonDto>> transform(JPAQuery<LessonSearch> query) {
        return query.transform(GroupBy.groupBy(qLessonSearch.date)
                .as(GroupBy.list(getLessonBoardProjection())));
    }

    default ConstructorExpression<LessonBoardLessonDto> getLessonBoardProjection() {
        return Projections.constructor(LessonBoardLessonDto.class, qLessonSearch.id, qLessonSearch.date, qLessonSearch.number,
                getLessonBoardTypeProjection(),
                getLessonBoardLocationProjection().skipNulls(),
                getLessonBoardSubjectProjection(),
                getLessonBoardUserProjection(),
                Projections.list(getLessonBoardGroupProjection()),
                qLessonSearch.online);
    }

    default QBean<LessonBoardTypeDto> getLessonBoardTypeProjection() {
        return Projections.bean(LessonBoardTypeDto.class, qLessonSearch.type.id, qLessonSearch.type.name, qLessonSearch.type.shortName);
    }

    default QBean<LessonBoardSubjectDto> getLessonBoardSubjectProjection() {
        return Projections.bean(LessonBoardSubjectDto.class, qLessonSearch.subject.id, qLessonSearch.subject.name);
    }

    default QBean<LessonBoardGroupDto> getLessonBoardGroupProjection() {
        return Projections.bean(LessonBoardGroupDto.class, qGroup.id, qGroup.name);
    }

    default QBean<LessonBoardUserDto> getLessonBoardUserProjection() {
        return Projections.bean(LessonBoardUserDto.class, qLessonSearch.teacher.id,
                qLessonSearch.teacher.firstName, qLessonSearch.teacher.lastName, qLessonSearch.teacher.personalLink);
    }

    default QBean<LessonBoardLocationDto> getLessonBoardLocationProjection() {
        return Projections.bean(LessonBoardLocationDto.class, qLessonSearch.location.id,
                qLessonSearch.location.name, qLessonSearch.location.typeShortName, qLessonSearch.location.address);
    }

}