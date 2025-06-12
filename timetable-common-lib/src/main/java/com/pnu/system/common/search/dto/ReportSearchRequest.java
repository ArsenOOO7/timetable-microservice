package com.pnu.system.common.search.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ReportSearchRequest extends BaseSearchRequest {

    @Valid
    @NotEmpty
    private List<SearchField> fields;
    @Valid
    private List<SearchCondition> conditions;
    @Valid
    private List<SearchOrderByField> orderByFields;

    public List<SearchCondition> getConditions() {
        if (Objects.isNull(conditions)) {
            conditions = new ArrayList<>();
        }
        return conditions;
    }

    public List<SearchOrderByField> getOrderByFields() {
        if (Objects.isNull(orderByFields)) {
            orderByFields = new ArrayList<>();
        }
        return orderByFields;
    }
}
