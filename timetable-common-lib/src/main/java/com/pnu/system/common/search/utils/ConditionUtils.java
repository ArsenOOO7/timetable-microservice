package com.pnu.system.common.search.utils;

import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.constant.DataType;
import com.pnu.system.common.search.dto.SearchCondition;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConditionUtils {

    public static SearchCondition build(String fieldName, Object value) {
        return build(fieldName, value, ConditionOperation.EQUAL);
    }

    public static SearchCondition build(String fieldName, Object value, ConditionOperation operation) {
        return build(fieldName, value, operation, DataType.STRING);
    }

    public static SearchCondition build(String fieldName, Object value, ConditionOperation operation, DataType dataType) {
        return SearchCondition.builder()
                .fieldName(fieldName)
                .value(value)
                .operation(operation)
                .dataType(dataType)
                .build();
    }
}
