package com.pnu.system.common.search.utils;

import com.pnu.system.common.search.constant.OrderBy;
import com.pnu.system.common.search.dto.SearchOrderByField;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderByUtils {

    public static SearchOrderByField build(String field) {
        return build(field, OrderBy.DESC);
    }

    public static SearchOrderByField build(String fieldName, OrderBy orderBy) {
        return SearchOrderByField.builder()
                .fieldName(fieldName)
                .orderBy(orderBy)
                .build();
    }

}
