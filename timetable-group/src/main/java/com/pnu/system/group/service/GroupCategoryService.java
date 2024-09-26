package com.pnu.system.group.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.group.api.dto.GroupCategoryCreateRequest;
import com.pnu.system.group.api.dto.GroupCategoryResponseDto;
import com.pnu.system.group.api.dto.GroupCategoryUpdateRequest;
import com.pnu.system.group.domain.GroupCategory;
import com.pnu.system.group.mapper.GroupCategoryMapper;
import com.pnu.system.group.repository.GroupCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupCategoryService extends AbstractPersistenceService<GroupCategory> {

    private final GroupCategoryMapper mapper;
    private final GroupCategoryRepository repository;

    public GroupCategoryResponseDto create(GroupCategoryCreateRequest request) {
        GroupCategory groupCategory = super.create(mapper.asGroupCategory(request));
        return mapper.asGroupCategoryResponseDto(groupCategory);
    }

    public GroupCategoryResponseDto update(GroupCategoryUpdateRequest request) {
        GroupCategory groupCategory = super.update(mapper.asGroupCategory(request));
        return mapper.asGroupCategoryResponseDto(groupCategory);
    }

    public List<GroupCategoryResponseDto> getList() {
        return mapper.asGroupCategoryResponseDtos(repository.findAll());
    }

    @Override
    protected Class<GroupCategory> getEntityType() {
        return GroupCategory.class;
    }

    @Override
    protected JpaRepository<GroupCategory, String> getRepository() {
        return repository;
    }
}
