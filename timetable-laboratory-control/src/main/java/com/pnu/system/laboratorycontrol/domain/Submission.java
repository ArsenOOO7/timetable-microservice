package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.common.domain.AuditableEntity;
import com.pnu.system.laboratorycontrol.constant.SubmissionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "submission")
public class Submission extends AuditableEntity {

    @Column(name = "sub_assignment_id")
    private String subAssignmentId;
    @Column(name = "author_id")
    private String authorId;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "status")
    private SubmissionStatus status;
    @Column(name = "submit_date")
    private ZonedDateTime submitDate;

}
