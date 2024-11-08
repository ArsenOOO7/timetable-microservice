package com.pnu.system.group.repository;

import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.group.domain.Group;
import com.pnu.system.group.domain.QGroup;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface GroupSnapshotRepository extends JpaRepository<Group, String> {

    QGroup qGroup = QGroup.group;

    default List<GroupSnapshotDto> getModifiedAfterDate(ZonedDateTime lastModifiedAt) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(GroupSnapshotDto.class, qGroup.id, qGroup.name, qGroup.lastModifiedAt))
                .from(qGroup)
                .where(qGroup.lastModifiedAt.after(lastModifiedAt))
                .fetch();
    }

    default GroupSnapshotDto getSnapshotById(String id) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(GroupSnapshotDto.class, qGroup.id, qGroup.name, qGroup.lastModifiedAt))
                .from(qGroup)
                .where(qGroup.id.eq(id))
                .fetchOne();
    }

    default List<GroupSnapshotDto> getByIds(List<String> ids) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(GroupSnapshotDto.class, qGroup.id, qGroup.name, qGroup.lastModifiedAt))
                .from(qGroup)
                .where(qGroup.id.in(ids))
                .fetch();
    }
}
