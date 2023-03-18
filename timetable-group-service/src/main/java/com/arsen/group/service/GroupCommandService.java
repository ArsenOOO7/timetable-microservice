package com.arsen.group.service;

import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.repository.GroupRepository;
import com.arsen.group.transformer.GroupTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupCommandService {

    private final GroupRepository groupRepository;
    private final GroupReadService groupWriteService;

    public GroupDto create(GroupDto groupDto){

        if(groupDto == null){
            throw new NullPointerException("Group cannot be null!");
        }

        Group group = GroupTransformer.convetDtoToGroup(groupDto);
        groupCollective(group, groupDto.getGroupIds());
        group = groupRepository.save(group);

        return GroupTransformer.convertEntityToDto(
            group, groupDto.getGroupIds()
        );
    }

    public void update(GroupDto groupDto){

        if(groupDto == null){
            throw new NullPointerException("Group cannot be null!");
        }

        Group group = groupWriteService.readByIdWithGroups(groupDto.getId());
        GroupTransformer.copyValues(group, groupDto);
        groupCollective(group, groupDto.getGroupIds());

        groupRepository.save(group);

    }


    public void delete(long id){
        groupRepository.deleteById(id);
    }



    private void groupCollective(Group group, Set<Long> groupIds){

        if(!group.isCollective()){
            return;
        }

        //TODO: Think about it
        if(group.getGroups().size() != groupIds.size()) {
            Set<Group> groups = groupWriteService.readGroups(groupIds);
            group.getGroups().addAll(groups);
        }
    }

}
