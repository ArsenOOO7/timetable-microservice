package com.pnu.system.academiccatalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "specialty")
public class Specialty extends AbstractSpecialty {

    @Column(name = "domain_id")
    private String knowledgeDomainId;

}
