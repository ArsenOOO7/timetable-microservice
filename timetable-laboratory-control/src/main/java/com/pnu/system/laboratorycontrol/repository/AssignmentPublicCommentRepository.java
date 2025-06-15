package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentCommentSearchRequest;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPublicComment;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPublicCommentSearch;
import com.pnu.system.laboratorycontrol.domain.comment.QAssignmentPublicCommentSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentPublicCommentRepository extends JpaRepository<AssignmentPublicComment, String> {

    QAssignmentPublicCommentSearch qAssignmentPublicCommentSearch = QAssignmentPublicCommentSearch.assignmentPublicCommentSearch;

    default List<AssignmentPublicCommentSearch> getList(AssignmentCommentSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qAssignmentPublicCommentSearch)
                .leftJoin(qAssignmentPublicCommentSearch.author)
                .where(qAssignmentPublicCommentSearch.assignmentId.eq(request.getAssignmentId()))
                .orderBy(qAssignmentPublicCommentSearch.createdAt.asc())
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }

    void deleteAllByAssignmentId(String assignmentId);
}
