package com.pnu.system.common.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserType {

    SUPER_ADMIN(RoleType.SUPER_ADMIN),
    INTERNAL_ADMIN(RoleType.INTERNAL_ADMIN),
    ADMIN(RoleType.ADMIN),
    TEACHER(RoleType.TEACHER),
    USER(RoleType.USER);

    private final RoleType roleType;

    public RoleType roleType() {
        return roleType;
    }
}
