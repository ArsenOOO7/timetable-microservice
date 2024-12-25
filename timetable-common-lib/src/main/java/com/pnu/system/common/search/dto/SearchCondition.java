package com.pnu.system.common.search.dto;

import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.constant.DataType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCondition {

    @NotBlank
    private String fieldName;
    @NotNull
    private Object value;
    @NotNull
    private ConditionOperation operation;
    @NotNull
    private DataType dataType;
    private boolean collectionField;

}
