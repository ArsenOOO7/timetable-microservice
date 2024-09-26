package com.pnu.system.group.mapper;

import com.pnu.system.group.api.dto.GroupCreateRequest;
import com.pnu.system.group.api.dto.GroupResponseDto;
import com.pnu.system.group.api.dto.GroupUpdateRequest;
import com.pnu.system.group.domain.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "relatedGroupIds", ignore = true)
    @Mapping(target = "groupCategoryIds", ignore = true)
    Group asGroup(GroupCreateRequest groupCreateRequest);

    @Mapping(target = "relatedGroupIds", ignore = true)
    @Mapping(target = "groupCategoryIds", ignore = true)
    Group asGroup(GroupUpdateRequest groupUpdateRequest);

    GroupResponseDto asGroupResponseDto(Group group);

    List<GroupResponseDto> asGroupResponseDtos(List<Group> list);

}
