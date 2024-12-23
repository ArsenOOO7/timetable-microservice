package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.DepartmentSearch;
import com.pnu.system.academiccatalog.domain.QDepartmentSearch;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentSearchRepository extends AbstractSearchRepository<DepartmentSearch> {
    @Override
    protected EntityPathBase<DepartmentSearch> getPath() {
        return QDepartmentSearch.departmentSearch;
    }
}
