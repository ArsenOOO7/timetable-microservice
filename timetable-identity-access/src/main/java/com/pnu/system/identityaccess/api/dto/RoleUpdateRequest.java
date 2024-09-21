package com.pnu.system.identityaccess.api.dto;

import com.pnu.system.common.constant.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleUpdateRequest {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private RoleType type;
    private List<String> permissionIds;
    @NotNull
    private Integer version;

}
