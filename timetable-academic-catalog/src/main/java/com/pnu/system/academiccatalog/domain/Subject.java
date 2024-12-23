package com.pnu.system.academiccatalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject extends AbstractSubject {

    @Column(name = "educational_program_id")
    private String educationalProgramId;

}
