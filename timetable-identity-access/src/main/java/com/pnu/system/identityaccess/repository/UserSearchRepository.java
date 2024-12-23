package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.identityaccess.domain.QUser;
import com.pnu.system.identityaccess.domain.User;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class UserSearchRepository extends AbstractSearchRepository<User> {


    @Override
    protected EntityPathBase<User> getPath() {
        return QUser.user;
    }
}
