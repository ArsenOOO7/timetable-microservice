package com.pnu.system.timetable.domain;

import com.pnu.system.common.domain.VersionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lesson_type")
public class LessonType extends VersionEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;

}
