package com.pnu.system.laboratorycontrol.api.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentPrivateCommentCreateRequest {

    @NotBlank
    private String assignmentId;
    @NotBlank
    private String replyToUserId;
    @NotBlank
    private String message;

}
