package com.pnu.system.group.domain;

import com.pnu.system.common.domain.AuditableEntity;
import com.pnu.system.group.constant.GroupType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractGroup extends AuditableEntity {

    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "specialty_id")
    private String specialtyId;
    @Column(name = "academic_year")
    private int academicYear;
    @Column(name = "number")
    private int number;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GroupType type;
    @Column(name = "name")
    private String name;

}
