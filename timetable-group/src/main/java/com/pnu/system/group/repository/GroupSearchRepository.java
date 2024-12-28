package com.pnu.system.group.repository;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.group.domain.GroupSearch;
import com.pnu.system.group.domain.QGroupSearch;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class GroupSearchRepository extends AbstractSearchRepository<GroupSearch> {
    @Override
    protected EntityPathBase<GroupSearch> getPath() {
        return QGroupSearch.groupSearch;
    }
}
