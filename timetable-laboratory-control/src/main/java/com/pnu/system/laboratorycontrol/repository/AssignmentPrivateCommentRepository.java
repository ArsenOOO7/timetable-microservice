package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentSearchRequest;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPrivateComment;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPrivateCommentSearch;
import com.pnu.system.laboratorycontrol.domain.comment.QAssignmentPrivateCommentSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentPrivateCommentRepository extends JpaRepository<AssignmentPrivateComment, String> {

    QAssignmentPrivateCommentSearch qAssignmentPrivateCommentSearch = QAssignmentPrivateCommentSearch.assignmentPrivateCommentSearch;

    default List<AssignmentPrivateCommentSearch> getList(AssignmentPrivateCommentSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qAssignmentPrivateCommentSearch)
                .leftJoin(qAssignmentPrivateCommentSearch.author)
                .leftJoin(qAssignmentPrivateCommentSearch.replyToUser)
                .where(qAssignmentPrivateCommentSearch.assignmentId.eq(request.getAssignmentId()),
                        qAssignmentPrivateCommentSearch.author.id.eq(request.getUserId())
                                .or(qAssignmentPrivateCommentSearch.replyToUser.id.in(request.getUserId())))
                .orderBy(qAssignmentPrivateCommentSearch.createdAt.asc())
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }

    void deleteAllByAssignmentId(String assignmentId);

}
