package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.identityaccess.domain.QUserWithTeacherProfile;
import com.pnu.system.identityaccess.domain.UserWithTeacherProfile;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSnapshotRepository extends JpaRepository<UserWithTeacherProfile, String> {

    QUserWithTeacherProfile qUserWithTeacherProfile = QUserWithTeacherProfile.userWithTeacherProfile;

    default UserSnapshotDto getSnapshotById(String id) {
        return QueryDslFactory.getQueryFactory()
                .select(getUserSnapshotProjection())
                .from(qUserWithTeacherProfile)
                .where(qUserWithTeacherProfile.id.eq(id))
                .fetchOne();
    }

    default List<UserSnapshotDto> getByIds(List<String> ids) {
        return QueryDslFactory.getQueryFactory()
                .select(getUserSnapshotProjection())
                .from(qUserWithTeacherProfile)
                .where(qUserWithTeacherProfile.id.in(ids))
                .fetch();
    }


    default ConstructorExpression<UserSnapshotDto> getUserSnapshotProjection() {
        return Projections.constructor(UserSnapshotDto.class, qUserWithTeacherProfile.id, qUserWithTeacherProfile.firstName, qUserWithTeacherProfile.lastName,
                qUserWithTeacherProfile.personalLink, qUserWithTeacherProfile.type);
    }
}
