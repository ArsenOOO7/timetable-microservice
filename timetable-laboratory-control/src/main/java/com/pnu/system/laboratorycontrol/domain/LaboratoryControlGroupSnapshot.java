package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.common.domain.BaseSnapshotEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "group_snapshot")
public class LaboratoryControlGroupSnapshot extends BaseSnapshotEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "deleted")
    private boolean deleted;

}
