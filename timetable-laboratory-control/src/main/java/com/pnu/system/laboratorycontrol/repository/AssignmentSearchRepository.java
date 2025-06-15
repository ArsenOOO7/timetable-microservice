package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.laboratorycontrol.domain.AssignmentSearch;
import com.pnu.system.laboratorycontrol.domain.QAssignmentSearch;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentSearchRepository extends AbstractSearchRepository<AssignmentSearch> {
    @Override
    protected EntityPathBase<AssignmentSearch> getPath() {
        return QAssignmentSearch.assignmentSearch;
    }
}
