package com.pnu.system.academiccatalog.domain;

import com.pnu.system.common.domain.VersionEntity;
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
@Table(name = "educational_program")
public class EducationalProgram extends VersionEntity {

    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

}
