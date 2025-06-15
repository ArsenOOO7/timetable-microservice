package com.pnu.system.laboratorycontrol.api.dto.comment;

import com.pnu.system.common.search.dto.ReportSearchRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentSearchRequest extends ReportSearchRequest {

    @NotBlank
    private String courseId;

}
