package com.pnu.system.identityaccess.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherProfileUpdateRequest {

    @NotBlank
    private String userId;
    private String personalLink;
    private String academicStatusId;
    private String chairId;

    @NotNull
    private Integer version;

}
