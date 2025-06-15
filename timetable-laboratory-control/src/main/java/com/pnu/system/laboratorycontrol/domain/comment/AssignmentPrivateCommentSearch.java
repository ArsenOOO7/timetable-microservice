package com.pnu.system.laboratorycontrol.domain.comment;

import com.pnu.system.laboratorycontrol.domain.LaboratoryControlUserSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assignment_private_comment")
public class AssignmentPrivateCommentSearch extends AbstractComment {

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private LaboratoryControlUserSnapshot author;
    @ManyToOne
    @JoinColumn(name = "reply_to_user_id", insertable = false, updatable = false)
    private LaboratoryControlUserSnapshot replyToUser;

}
