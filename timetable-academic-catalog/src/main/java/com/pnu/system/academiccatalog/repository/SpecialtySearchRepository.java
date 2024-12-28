package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.QSpecialtySearch;
import com.pnu.system.academiccatalog.domain.SpecialtySearch;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialtySearchRepository extends AbstractSearchRepository<SpecialtySearch> {
    @Override
    protected EntityPathBase<SpecialtySearch> getPath() {
        return QSpecialtySearch.specialtySearch;
    }
}
