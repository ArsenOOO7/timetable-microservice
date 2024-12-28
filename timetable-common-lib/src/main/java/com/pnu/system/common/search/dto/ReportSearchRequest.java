package com.pnu.system.common.search.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

}
