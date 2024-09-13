package com.pnu.system.common.secutity.model;

import com.pnu.system.common.constant.PermissionName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetails {

    private String id;
    private List<PermissionName> permissions;

}
