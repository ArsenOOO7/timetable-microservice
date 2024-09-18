package com.pnu.system.lessonlocation.domain;


import com.pnu.system.common.domain.VersionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lesson_location")
public class LessonLocation extends VersionEntity {
    @OneToOne
    @JoinColumn(name = "type_id")
    private LocationType locationType;
    @Column(name = "name")
    @NotNull
    private String name;
    @Column(name = "address")
    private String address;
}
