package com.pnu.system.laboratorycontrol.domain.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assignment_public_comment")
public class AssignmentPublicComment extends AbstractComment {

}
