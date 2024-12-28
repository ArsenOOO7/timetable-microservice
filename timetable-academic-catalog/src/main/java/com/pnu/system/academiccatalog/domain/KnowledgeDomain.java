package com.pnu.system.academiccatalog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "knowledge_domain")
public class KnowledgeDomain extends AbstractKnowledgeDomain {


}
