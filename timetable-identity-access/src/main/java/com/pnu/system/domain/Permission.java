package com.pnu.system.domain;

import com.pnu.system.common.domain.BaseEntity;
import com.pnu.system.constant.PermissionGroup;
import com.pnu.system.constant.PermissionName;
import com.pnu.system.constant.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private PermissionName name;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "group")
    private PermissionGroup group;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoleType type;

}
