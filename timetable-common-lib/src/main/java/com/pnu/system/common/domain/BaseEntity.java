package com.pnu.system.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements BaseEntityProvider {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private String id;

}
