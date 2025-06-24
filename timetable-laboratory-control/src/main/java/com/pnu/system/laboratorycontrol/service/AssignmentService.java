package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentDto;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentUpdateRequest;
import com.pnu.system.laboratorycontrol.api.validation.AssignmentValidator;
import com.pnu.system.laboratorycontrol.domain.Assignment;
import com.pnu.system.laboratorycontrol.event.AssignmentCreateEvent;
import com.pnu.system.laboratorycontrol.mapper.AssignmentMapper;
import com.pnu.system.laboratorycontrol.repository.AssignmentPrivateCommentRepository;
import com.pnu.system.laboratorycontrol.repository.AssignmentPublicCommentRepository;
import com.pnu.system.laboratorycontrol.repository.AssignmentRepository;
import com.pnu.system.laboratorycontrol.repository.SubAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssignmentService extends AbstractPersistenceService<Assignment> {

    private final AssignmentMapper mapper;
    private final AssignmentValidator validator;
    private final AssignmentRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AssignmentPublicCommentRepository publicCommentRepository;
    private final AssignmentPrivateCommentRepository privateCommentRepository;
    private final SubAssignmentRepository subAssignmentRepository;

    public AssignmentDto create(AssignmentCreateRequest request) {
        validator.validateAssignment(request);
        Assignment assignment = super.create(mapper.asAssignment(request));
        applicationEventPublisher.publishEvent(new AssignmentCreateEvent(assignment));
        return mapper.asAssignmentDto(assignment);
    }

    public AssignmentDto update(AssignmentUpdateRequest request) {
        validator.validateAssignment(request);
        Assignment assignment = getOne(request.getId());
        mapper.applyUpdateRequest(assignment, request);
        return mapper.asAssignmentDto(super.update(assignment));
    }

    public AssignmentDto getOneById(String id) {
        return mapper.asAssignmentDto(getOne(id));
    }

    @Override
    public void delete(Assignment entity) {
        //TODO 6/15/25: Probably, it's make sense to set some flag 'deleted' and do it async, or in CRON-Job
        publicCommentRepository.deleteAllByAssignmentId(entity.getId());
        privateCommentRepository.deleteAllByAssignmentId(entity.getId());
        subAssignmentRepository.deleteAllByAssignmentId(entity.getId());
        super.delete(entity);
    }

    @Override
    protected Class<Assignment> getEntityType() {
        return Assignment.class;
    }

    @Override
    protected JpaRepository<Assignment, String> getRepository() {
        return repository;
    }
}
