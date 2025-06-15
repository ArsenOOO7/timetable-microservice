package com.pnu.system.laboratorycontrol.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assignment")
public class AssignmentSearch extends AbstractAssignment {


}
