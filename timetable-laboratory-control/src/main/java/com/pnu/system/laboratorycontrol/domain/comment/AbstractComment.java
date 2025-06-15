package com.pnu.system.laboratorycontrol.domain.comment;

import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractComment extends AuditableEntity {

    @Column(name = "assignment_id")
    private String assignmentId;
    @Column(name = "message")
    private String message;
    @Column(name = "author_id")
    private String authorId;

}
