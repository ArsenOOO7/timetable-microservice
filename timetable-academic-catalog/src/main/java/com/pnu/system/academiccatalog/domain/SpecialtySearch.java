package com.pnu.system.academiccatalog.domain;

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
public class SpecialtySearch extends AbstractSpecialty {

    @ManyToOne
    @JoinColumn(name = "domain_id", insertable = false, updatable = false)
    private KnowledgeDomain knowledgeDomain;

}
