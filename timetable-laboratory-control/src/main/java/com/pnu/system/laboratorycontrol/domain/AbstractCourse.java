package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.common.domain.AuditableEntity;
import com.pnu.system.laboratorycontrol.constant.CourseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractCourse extends AuditableEntity {

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CourseStatus status;

}
