package com.pnu.system.identityaccess.domain;

import com.pnu.system.common.constant.RoleType;
import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleSearch extends AuditableEntity {

    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoleType type;

}
