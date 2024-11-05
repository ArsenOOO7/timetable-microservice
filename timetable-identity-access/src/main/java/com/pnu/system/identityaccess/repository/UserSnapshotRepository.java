package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.identityaccess.domain.QTeacherProfile;
import com.pnu.system.identityaccess.domain.QUser;
import com.pnu.system.identityaccess.domain.User;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSnapshotRepository extends JpaRepository<User, String> {

    QUser qUser = QUser.user;
    QTeacherProfile qTeacherProfile = QTeacherProfile.teacherProfile;

    default UserSnapshotDto getSnapshotById(String id) {
        return QueryDslFactory.getQueryFactory()
                .select(getUserSnapshotProjection())
                .from(qUser)
                .leftJoin(qTeacherProfile).on(qTeacherProfile.userId.eq(qUser.id))
                .where(qUser.id.eq(id))
                .fetchOne();
    }

    default List<UserSnapshotDto> getByIds(List<String> ids) {
        return QueryDslFactory.getQueryFactory()
                .select(getUserSnapshotProjection())
                .from(qUser)
                .leftJoin(qTeacherProfile).on(qTeacherProfile.userId.eq(qUser.id))
                .where(qUser.id.in(ids))
                .fetch();
    }


    default ConstructorExpression<UserSnapshotDto> getUserSnapshotProjection() {
        return Projections.constructor(UserSnapshotDto.class, qUser.id, qUser.firstName, qUser.lastName,
                qTeacherProfile.personalLink, qUser.type);
    }
}
