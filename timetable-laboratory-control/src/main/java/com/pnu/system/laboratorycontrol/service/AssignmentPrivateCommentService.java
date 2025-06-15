package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentDto;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentSearchRequest;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPrivateComment;
import com.pnu.system.laboratorycontrol.mapper.AssignmentPrivateCommentMapper;
import com.pnu.system.laboratorycontrol.repository.AssignmentPrivateCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssignmentPrivateCommentService extends AbstractPersistenceService<AssignmentPrivateComment> {

    private final AssignmentPrivateCommentMapper mapper;
    private final AssignmentPrivateCommentRepository repository;

    public void create(AssignmentPrivateCommentCreateRequest request) {
        AssignmentPrivateComment comment = mapper.asAssignmentPrivateComment(request);
        super.create(comment);
    }

    public List<AssignmentPrivateCommentDto> getList(AssignmentPrivateCommentSearchRequest request) {
        return mapper.asPrivateCommentDtos(repository.getList(request));
    }

    @Override
    protected Class<AssignmentPrivateComment> getEntityType() {
        return AssignmentPrivateComment.class;
    }

    @Override
    protected JpaRepository<AssignmentPrivateComment, String> getRepository() {
        return repository;
    }
}
