package com.pnu.system.laboratorycontrol.api.dto.comment;

import com.pnu.system.common.search.dto.BaseSearchRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentCommentSearchRequest extends BaseSearchRequest {

    @NotBlank
    private String assignmentId;

}
