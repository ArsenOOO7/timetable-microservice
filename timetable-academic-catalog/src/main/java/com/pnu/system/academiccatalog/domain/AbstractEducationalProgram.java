package com.pnu.system.academiccatalog.domain;

import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractEducationalProgram extends AuditableEntity {

    @Column(name = "name")
    private String name;

}
