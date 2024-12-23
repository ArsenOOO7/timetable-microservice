package com.pnu.system.common.search.dto;

import com.pnu.system.common.dto.BaseSearchRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportSearchRequest extends BaseSearchRequest {

    private List<SearchField> fields;

}
