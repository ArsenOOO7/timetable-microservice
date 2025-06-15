package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentCommentDto;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentCommentSearchRequest;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPublicCommentCreateRequest;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPublicComment;
import com.pnu.system.laboratorycontrol.mapper.AssignmentPublicCommentMapper;
import com.pnu.system.laboratorycontrol.repository.AssignmentPublicCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssignmentPublicCommentService extends AbstractPersistenceService<AssignmentPublicComment> {

    private final AssignmentPublicCommentMapper mapper;
    private final AssignmentPublicCommentRepository repository;

    public void create(AssignmentPublicCommentCreateRequest request) {
        AssignmentPublicComment comment = mapper.asAssignmentPrivateComment(request);
        super.create(comment);
    }

    public List<AssignmentCommentDto> getList(AssignmentCommentSearchRequest request) {
        return mapper.asCommentDtos(repository.getList(request));
    }

    @Override
    protected Class<AssignmentPublicComment> getEntityType() {
        return AssignmentPublicComment.class;
    }

    @Override
    protected JpaRepository<AssignmentPublicComment, String> getRepository() {
        return repository;
    }

}
