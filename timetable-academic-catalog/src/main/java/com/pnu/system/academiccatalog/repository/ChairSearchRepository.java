package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.ChairSearch;
import com.pnu.system.academiccatalog.domain.QChairSearch;
import com.pnu.system.common.search.AbstractSearchRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public class ChairSearchRepository extends AbstractSearchRepository<ChairSearch> {
    @Override
    protected EntityPathBase<ChairSearch> getPath() {
        return QChairSearch.chairSearch;
    }
}
