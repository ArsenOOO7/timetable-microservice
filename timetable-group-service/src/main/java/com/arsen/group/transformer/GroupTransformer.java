package com.arsen.group.transformer;

import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.event.GroupEventUpdate;

import java.util.HashSet;
import java.util.Set;

public class GroupTransformer {

    public static Group convetDtoToGroup(GroupDto groupDto){
        return new Group(
            groupDto.getId(),
            groupDto.getCypher(),
            groupDto.getAcademicYear(),
            groupDto.getNumber(),
            groupDto.isMaster(),
            groupDto.isCollege(),
            groupDto.isCollective(),
            new HashSet<>(),
            new HashSet<>()
        );
    }


    public static GroupDto convertEntityToDto(Group group, Set<Long> ids){
        return new GroupDto(
                group.getId(),
                group.getCypher(),
                group.getAcademicYear(),
                group.getNumber(),
                group.isMaster(),
                group.isCollege(),
                group.isCollective(),
                ids
        );
    }


    public static GroupEventUpdate convertToGroupEvent(GroupDto groupDto, GroupEventUpdate.GroupStatus status){
        return new GroupEventUpdate(
                groupDto.getId(),
                groupDto.toString(),
                groupDto.isCollective(),
                status,
                groupDto.getGroupIds()
        );
    }


    public static void copyValues(Group group, GroupDto groupDto){

        group.setCypher(groupDto.getCypher());
        group.setAcademicYear(groupDto.getAcademicYear());
        group.setNumber(groupDto.getNumber());

        group.setMaster(groupDto.isMaster());
        group.setCollege(groupDto.isCollege());

    }


}
