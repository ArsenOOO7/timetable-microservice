package com.pnu.system.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public class BaseSnapshotEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "last_modified_at")
    private ZonedDateTime lastModifiedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseSnapshotEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
