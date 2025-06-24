package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.laboratorycontrol.api.dto.SubmissionDto;
import com.pnu.system.laboratorycontrol.constant.SubmissionStatus;
import com.pnu.system.laboratorycontrol.domain.Submission;
import com.pnu.system.laboratorycontrol.mapper.SubmissionMapper;
import com.pnu.system.laboratorycontrol.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubmissionService extends AbstractPersistenceService<Submission> {

    private final SubmissionMapper mapper;
    private final SubmissionRepository repository;

    public Submission create(String subAssignmentId) {
        Submission submission = mapper.asSubmission(subAssignmentId);
        return super.create(submission);
    }

    public void updateStatus(String id, SubmissionStatus status) {
        Submission submission = getOne(id);
        submission.setStatus(status);
        super.update(submission);
    }

    public List<SubmissionDto> getList(String subAssignmentId, String authorId) {
        return mapper.asSubmissionDtos(repository.findAllBySubAssignmentIdAndAuthorId(subAssignmentId, authorId));
    }

    @Override
    protected Class<Submission> getEntityType() {
        return Submission.class;
    }

    @Override
    protected JpaRepository<Submission, String> getRepository() {
        return repository;
    }
}
