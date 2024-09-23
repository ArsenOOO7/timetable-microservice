package com.pnu.system.academiccatalog.domain;

import com.pnu.system.common.domain.VersionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "knowledge_domain")
public class KnowledgeDomain extends VersionEntity {

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

}
