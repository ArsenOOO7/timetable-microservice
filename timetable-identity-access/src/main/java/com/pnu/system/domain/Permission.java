package com.pnu.system.domain;

import com.pnu.system.common.domain.BaseEntity;
import com.pnu.system.domain.enums.PermissionName;
import com.pnu.system.domain.enums.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {
    @NotNull
    @Length(max = 64)
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private PermissionName name;
    @NotNull
    @Length(max = 20)
    @Column(name = "group")
    private String group;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoleType type;
}
