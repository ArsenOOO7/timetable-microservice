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
@Table(name = "subject_snapshot")
public class TimetableSubjectSnapshot extends BaseSnapshotEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "deleted")
    private boolean deleted;

}
