package com.pnu.system.group.mapper;

import com.pnu.system.group.api.dto.GroupCategoryCreateRequest;
import com.pnu.system.group.api.dto.GroupCategoryResponseDto;
import com.pnu.system.group.api.dto.GroupCategoryUpdateRequest;
import com.pnu.system.group.domain.GroupCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupCategoryMapper {

    GroupCategory asGroupCategory(GroupCategoryCreateRequest groupCategoryCreateRequest);

    GroupCategory asGroupCategory(GroupCategoryUpdateRequest groupCategoryCreateRequest);

    GroupCategoryResponseDto asGroupCategoryResponseDto(GroupCategory groupCategory);

    List<GroupCategoryResponseDto> asGroupCategoryResponseDtos(List<GroupCategory> groupCategories);

}
