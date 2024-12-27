package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.identityaccess.domain.QUserSearch;
import com.pnu.system.identityaccess.domain.UserSearch;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class UserSearchRepository extends AbstractSearchRepository<UserSearch> {


    @Override
    protected EntityPathBase<UserSearch> getPath() {
        return QUserSearch.userSearch;
    }
}
