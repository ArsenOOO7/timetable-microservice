package com.pnu.system.academiccatalog.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chair")
public class Chair extends AbstractChair {

    @ElementCollection
    @CollectionTable(name = "chair_specialties", joinColumns = @JoinColumn(name = "specialty_id"))
    @Column(name = "chair_id")
    private List<String> specialtyIds;
    @Column(name = "department_id")
    private String departmentId;

}
