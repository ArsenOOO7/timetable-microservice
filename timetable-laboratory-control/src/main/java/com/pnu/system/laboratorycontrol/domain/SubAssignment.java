package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "sub_assignment")
public class SubAssignment extends AuditableEntity {

    @Column(name = "assignment_id")
    private String assignmentId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "max_grade")
    private Integer maxGrade;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "deadline")
    private ZonedDateTime deadline;

}
