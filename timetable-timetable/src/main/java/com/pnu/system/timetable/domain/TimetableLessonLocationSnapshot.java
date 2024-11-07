package com.pnu.system.timetable.domain;

import com.pnu.system.common.domain.BaseSnapshotEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lesson_location_snapshot")
public class TimetableLessonLocationSnapshot extends BaseSnapshotEntity {

    @Column(name = "type_short_name")
    private String typeShortName;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "deleted")
    private boolean deleted;

}
