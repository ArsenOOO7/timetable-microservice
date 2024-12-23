package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.QSubjectSearch;
import com.pnu.system.academiccatalog.domain.SubjectSearch;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectSearchRepository extends AbstractSearchRepository<SubjectSearch> {
    @Override
    protected EntityPathBase<SubjectSearch> getPath() {
        return QSubjectSearch.subjectSearch;
    }
}
