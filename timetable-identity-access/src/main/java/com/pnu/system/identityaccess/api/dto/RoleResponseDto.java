package com.pnu.system.identityaccess.api.dto;

import com.pnu.system.common.constant.RoleType;
import com.pnu.system.identityaccess.domain.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleResponseDto {

    private String name;
    private RoleType type;
    private List<Permission> permissions;

}
