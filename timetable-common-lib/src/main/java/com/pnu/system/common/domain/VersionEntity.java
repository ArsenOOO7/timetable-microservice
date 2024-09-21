package com.pnu.system.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class VersionEntity extends BaseEntity {

    @Version
    @Column(name = "version")
    private Integer version;

}
