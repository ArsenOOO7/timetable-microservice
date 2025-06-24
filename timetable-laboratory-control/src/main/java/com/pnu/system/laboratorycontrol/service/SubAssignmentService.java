package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentDto;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentUpdateRequest;
import com.pnu.system.laboratorycontrol.api.validation.SubAssignmentValidator;
import com.pnu.system.laboratorycontrol.domain.SubAssignment;
import com.pnu.system.laboratorycontrol.event.AssignmentCreateEvent;
import com.pnu.system.laboratorycontrol.mapper.SubAssignmentMapper;
import com.pnu.system.laboratorycontrol.repository.SubAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubAssignmentService extends AbstractPersistenceService<SubAssignment> {

    private final SubAssignmentMapper mapper;
    private final SubAssignmentValidator validator;
    private final SubAssignmentRepository repository;

    public SubAssignmentDto create(SubAssignmentCreateRequest request) {
        validator.validate(request);
        SubAssignment subAssignment = mapper.asSubAssignment(request);
        return mapper.asSubAssignmentDto(super.create(subAssignment));
    }

    public SubAssignmentDto update(SubAssignmentUpdateRequest request) {
        validator.validate(request);
        SubAssignment subAssignment = getOne(request.getId());
        mapper.applySubAssignmentUpdateRequest(subAssignment, request);
        return mapper.asSubAssignmentDto(super.update(subAssignment));
    }

    public List<SubAssignmentDto> getList(String assignmentId) {
        return mapper.asSubAssignmentDtos(repository.findAllByAssignmentId(assignmentId));
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onAssignmentCreate(AssignmentCreateEvent event) {
        SubAssignment subAssignment = mapper.asSubAssignment(event.getAssignment());
        super.create(subAssignment);
    }

    @Override
    protected Class<SubAssignment> getEntityType() {
        return SubAssignment.class;
    }

    @Override
    protected JpaRepository<SubAssignment, String> getRepository() {
        return repository;
    }
}
