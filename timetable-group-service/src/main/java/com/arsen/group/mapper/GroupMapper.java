package com.arsen.group.mapper;

import com.arsen.common.event.EntityStatus;
import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.dto.GroupResponseDto;
import com.arsen.group.event.GroupEventUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Mapper
public interface GroupMapper {

    @Mappings({
        @Mapping(target = "master", defaultValue = "false"),
        @Mapping(target = "college", defaultValue = "false"),
        @Mapping(target = "collective", defaultValue = "false"),
        @Mapping(target = "groups", ignore = true),
        @Mapping(target = "collectiveGroups", ignore = true)
    })
    Group fromDto(GroupDto groupDto);

    @Mapping(target = "groupIds", expression = "java(mapToId(group.getGroups()))")
    GroupResponseDto toDto(Group group);

    @Mapping(target = "collective", ignore = true)
    Group update(GroupDto groupDto, @MappingTarget Group group);

    @Mapping(target = "fullName", expression = "java(group.toString())")
    @Mapping(target = "groupIds", expression = "java(mapToId(group.getGroups()))")
    GroupEventUpdate toEvent(Group group, EntityStatus status);


    default Set<Long> mapToId(Set<Group> groupSet){
        return groupSet.stream().map(Group::getId).collect(toSet());
    }

}
