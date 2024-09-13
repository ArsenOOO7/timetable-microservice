package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.identityaccess.api.dto.UserPreviewDto;
import com.pnu.system.identityaccess.domain.QUser;
import com.pnu.system.identityaccess.domain.User;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    QUser qUser = QUser.user;

    Optional<User> findByEmail(String email);

    default List<UserPreviewDto> getPreviewUsers() {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.bean(UserPreviewDto.class,
                        qUser.id,
                        qUser.firstName,
                        qUser.lastName,
                        qUser.email,
                        qUser.type
                ))
                .from(qUser)
                .fetch();
    }
}
