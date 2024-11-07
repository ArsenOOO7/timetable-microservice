package com.pnu.system.lessonlocation.domain;


import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lesson_location")
public class LessonLocation extends AuditableEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "type_id")
    private LocationType locationType;

}
