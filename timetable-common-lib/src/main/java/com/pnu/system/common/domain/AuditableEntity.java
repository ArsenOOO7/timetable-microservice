package com.pnu.system.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditableEntity extends VersionEntity {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private ZonedDateTime lastModifiedAt;

}
