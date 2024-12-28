package com.pnu.system.group.service;

import com.pnu.system.common.exception.ValidationException;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.group.api.dto.GroupCreateRequest;
import com.pnu.system.group.api.dto.GroupResponseDto;
import com.pnu.system.group.api.dto.GroupUpdateRequest;
import com.pnu.system.group.constant.GroupType;
import com.pnu.system.group.domain.Group;
import com.pnu.system.group.event.model.GroupCreateEvent;
import com.pnu.system.group.event.model.GroupDeleteEvent;
import com.pnu.system.group.event.model.GroupUpdateEvent;
import com.pnu.system.group.mapper.GroupMapper;
import com.pnu.system.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService extends AbstractPersistenceService<Group> {

    private final GroupMapper mapper;
    private final GroupRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final GroupCategoryService groupCategoryService;

    public GroupResponseDto create(GroupCreateRequest request) {
        Group group = mapper.asGroup(request);
        group.setGroupCategories(groupCategoryService.getAll(request.getGroupCategoryIds()));
        group.setRelatedGroups(getAll(request.getRelatedGroupIds()));
        eventPublisher.publishEvent(new GroupCreateEvent(group));
        return mapper.asGroupResponseDto(super.create(group));
    }

    public GroupResponseDto update(GroupUpdateRequest request) {
        Group group = mapper.asGroup(request);
        group.setGroupCategories(groupCategoryService.getAll(request.getGroupCategoryIds()));
        group.setRelatedGroups(getAll(request.getRelatedGroupIds()));
        eventPublisher.publishEvent(new GroupUpdateEvent(group));
        return mapper.asGroupResponseDto(super.update(group));
    }

    @Override
    public void delete(Group entity) {
        validateBeforeDelete(entity);
        eventPublisher.publishEvent(new GroupDeleteEvent(entity.getId()));
        super.delete(entity);
    }

    private void validateBeforeDelete(Group entity) {
        if (GroupType.FULL.equals(entity.getType())) {
            if (repository.existsByParentId(entity.getId())) {
                throw new ValidationException("There are subgroups related to this full group.");
            }
        }
    }

    @Override
    protected Class<Group> getEntityType() {
        return Group.class;
    }

    @Override
    protected JpaRepository<Group, String> getRepository() {
        return repository;
    }
}
