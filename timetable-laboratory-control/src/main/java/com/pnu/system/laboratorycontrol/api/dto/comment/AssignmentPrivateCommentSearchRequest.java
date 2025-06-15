package com.pnu.system.laboratorycontrol.api.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentPrivateCommentSearchRequest extends AssignmentCommentSearchRequest {

    @NotBlank
    private String userId;

}
