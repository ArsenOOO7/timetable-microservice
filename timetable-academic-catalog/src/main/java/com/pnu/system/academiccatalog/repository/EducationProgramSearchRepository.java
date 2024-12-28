package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.EducationalProgramSearch;
import com.pnu.system.academiccatalog.domain.QEducationalProgramSearch;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class EducationProgramSearchRepository extends AbstractSearchRepository<EducationalProgramSearch> {
    @Override
    protected EntityPathBase<EducationalProgramSearch> getPath() {
        return QEducationalProgramSearch.educationalProgramSearch;
    }
}
