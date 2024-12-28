package com.pnu.system.academiccatalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "educational_program")
public class EducationalProgram extends AbstractEducationalProgram {

    @Column(name = "specialty_id")
    private String specialtyId;

}
