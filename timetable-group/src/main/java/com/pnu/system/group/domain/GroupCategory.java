package com.pnu.system.group.domain;

import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "group_category")
public class GroupCategory extends AuditableEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;

}
