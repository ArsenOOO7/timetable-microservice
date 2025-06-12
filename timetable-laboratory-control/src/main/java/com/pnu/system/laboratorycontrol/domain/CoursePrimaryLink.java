package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_primary_link")
public class CoursePrimaryLink extends BaseEntity {

    @Column(name = "course_id")
    private String courseId;
    @Column(name = "link")
    private String link;
    @Column(name = "label")
    private String label;

}
