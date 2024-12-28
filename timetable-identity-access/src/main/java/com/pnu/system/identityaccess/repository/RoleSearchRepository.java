package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.identityaccess.domain.QRoleSearch;
import com.pnu.system.identityaccess.domain.RoleSearch;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class RoleSearchRepository extends AbstractSearchRepository<RoleSearch> {
    @Override
    protected EntityPathBase<RoleSearch> getPath() {
        return QRoleSearch.roleSearch;
    }
}
