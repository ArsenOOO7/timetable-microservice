package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.common.domain.AuditableEntity;
import com.pnu.system.laboratorycontrol.constant.AssignmentType;
import com.pnu.system.laboratorycontrol.constant.AssignmentVisibility;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class AbstractAssignment extends AuditableEntity {

    @Column(name = "course_id")
    private String courseId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "max_grade")
    private Integer maxGrade;
    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private AssignmentVisibility visibility;
    @Column(name = "appear_at")
    private ZonedDateTime appearAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AssignmentType type;
    @Column(name = "deadline")
    private ZonedDateTime deadline;

}
