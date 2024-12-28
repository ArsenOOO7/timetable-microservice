package com.pnu.system.common.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseSearchRequest {

    private int limit;
    private int offset;

}
