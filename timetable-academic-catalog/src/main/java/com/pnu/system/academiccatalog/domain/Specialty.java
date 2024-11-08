package com.pnu.system.academiccatalog.domain;

import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "specialty")
public class Specialty extends AuditableEntity {

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @ManyToOne
    @JoinColumn(name = "domain_id")
    private KnowledgeDomain knowledgeDomain;

}
