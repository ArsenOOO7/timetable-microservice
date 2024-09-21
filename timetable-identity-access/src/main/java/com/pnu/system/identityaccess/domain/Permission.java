package com.pnu.system.identityaccess.domain;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.constant.RoleType;
import com.pnu.system.common.domain.BaseEntity;
import com.pnu.system.identityaccess.constant.PermissionGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private PermissionName name;
    @Enumerated(EnumType.STRING)
    @Column(name = "group")
    private PermissionGroup group;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoleType type;

}
