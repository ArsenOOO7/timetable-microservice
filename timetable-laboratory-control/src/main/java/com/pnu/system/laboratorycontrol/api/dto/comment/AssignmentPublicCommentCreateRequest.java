package com.pnu.system.laboratorycontrol.api.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentPublicCommentCreateRequest {

    @NotBlank
    private String assignmentId;
    @NotBlank
    private String message;

}
