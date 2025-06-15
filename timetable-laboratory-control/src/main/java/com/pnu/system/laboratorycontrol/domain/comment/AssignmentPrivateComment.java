package com.pnu.system.laboratorycontrol.domain.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assignment_private_comment")
public class AssignmentPrivateComment extends AbstractComment {

    @Column(name = "reply_to_user_id")
    private String replyToUserId;

}
