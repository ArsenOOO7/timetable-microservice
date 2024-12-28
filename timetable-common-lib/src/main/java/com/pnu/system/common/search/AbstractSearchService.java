package com.pnu.system.common.search;

import com.pnu.system.common.domain.BaseEntity;
import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.common.search.dto.SearchField;

import java.util.List;
import java.util.Map;

import static com.pnu.system.common.constant.CommonFieldName.ID;

public abstract class AbstractSearchService<T extends BaseEntity> {

    public List<Map<String, Object>> search(ReportSearchRequest request) {
        request.getFields().add(new SearchField(ID));
        return getRepository().search(request);
    }

    protected abstract AbstractSearchRepository<T> getRepository();

}
