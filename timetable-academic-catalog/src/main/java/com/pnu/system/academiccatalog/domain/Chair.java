package com.pnu.system.academiccatalog.domain;

import com.pnu.system.common.domain.VersionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chair")
public class Chair extends VersionEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "chair_specialties",
            joinColumns = @JoinColumn(name = "chair_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Specialty> specialties;

    public List<Specialty> getSpecialties() {
        if (specialties == null) {
            specialties = new ArrayList<>();
        }
        return specialties;
    }
}
