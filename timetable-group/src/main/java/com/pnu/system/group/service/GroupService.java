package com.pnu.system.group.service;

import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.exception.ValidationException;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.group.api.dto.GroupCreateRequest;
import com.pnu.system.group.api.dto.GroupResponseDto;
import com.pnu.system.group.api.dto.GroupUpdateRequest;
import com.pnu.system.group.constant.GroupType;
import com.pnu.system.group.domain.Group;
import com.pnu.system.group.mapper.GroupMapper;
import com.pnu.system.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService extends AbstractPersistenceService<Group> {

    private final GroupMapper mapper;
    private final GroupRepository repository;
    private final GroupCategoryService groupCategoryService;

    public GroupResponseDto create(GroupCreateRequest request) {
        Group group = mapper.asGroup(request);
        group.setGroupCategories(groupCategoryService.getAll(request.getGroupCategoryIds()));
        group.setRelatedGroups(getAll(request.getRelatedGroupIds()));
        return mapper.asGroupResponseDto(super.create(group));
    }

    public GroupResponseDto update(GroupUpdateRequest request) {
        Group group = mapper.asGroup(request);
        group.setGroupCategories(groupCategoryService.getAll(request.getGroupCategoryIds()));
        group.setRelatedGroups(getAll(request.getRelatedGroupIds()));
        return mapper.asGroupResponseDto(super.update(group));
    }

    public List<GroupResponseDto> getList(BaseSearchRequest request) {
        return mapper.asGroupResponseDtos(repository.getList(request));
    }

    @Override
    public void delete(Group entity) {
        validateBeforeDelete(entity);
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
