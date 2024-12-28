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
@Table(name = "subject")
public class SubjectSearch extends AbstractSubject {

    @ManyToOne
    @JoinColumn(name = "educational_program_id", insertable = false, updatable = false)
    private EducationalProgram educationalProgram;

}
