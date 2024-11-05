package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.QSubject;
import com.pnu.system.academiccatalog.domain.Subject;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectSnapshotRepository extends JpaRepository<Subject, String> {

    QSubject qSubject = QSubject.subject;


    default SubjectSnapshotDto getSnapshotById(String id) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(SubjectSnapshotDto.class, qSubject.id, qSubject.name))
                .from(qSubject)
                .where(qSubject.id.eq(id))
                .fetchOne();
    }

    default List<SubjectSnapshotDto> getByIds(List<String> ids) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(SubjectSnapshotDto.class, qSubject.id, qSubject.name))
                .from(qSubject)
                .where(qSubject.id.in(ids))
                .fetch();
    }
}
