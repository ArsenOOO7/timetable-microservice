package com.pnu.system.common.search.dto;

import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.constant.DataType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {

    private String fieldName;
    private Object value;
    private ConditionOperation operation;
    private DataType dataType;
    private boolean collectionField;

}
