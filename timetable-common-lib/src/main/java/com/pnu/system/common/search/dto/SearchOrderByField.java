package com.pnu.system.common.search.dto;

import com.pnu.system.common.search.constant.OrderBy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchOrderByField {

    @NotBlank
    private String fieldName;
    @NotNull
    private OrderBy orderBy;

}
