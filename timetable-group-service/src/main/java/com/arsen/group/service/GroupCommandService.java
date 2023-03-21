package com.arsen.group.service;

import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.event.GroupEventUpdate;
import com.arsen.group.repository.GroupRepository;
import com.arsen.group.transformer.GroupTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupCommandService {

    private final GroupRepository groupRepository;
    private final GroupReadService groupReadService;

    private final StreamBridge streamBridge;

    public GroupDto create(GroupDto groupDto){

        if(groupDto == null){
            throw new NullPointerException("Group cannot be null!");
        }

        Group group = GroupTransformer.convetDtoToGroup(groupDto);
        groupCollective(group, groupDto.getGroupIds());
        group = groupRepository.save(group);

        groupDto = GroupTransformer.convertEntityToDto(
                group, groupDto.getGroupIds()
        );

        postUpdate(groupDto, GroupEventUpdate.GroupStatus.CREATED);
        return groupDto;
    }

    public void update(GroupDto groupDto){

        if(groupDto == null){
            throw new NullPointerException("Group cannot be null!");
        }

        Group group = groupReadService.readByIdWithGroups(groupDto.getId());
        GroupTransformer.copyValues(group, groupDto);
        groupCollective(group, groupDto.getGroupIds());

        group = groupRepository.save(group);

        groupDto = GroupTransformer.convertEntityToDto(
                group, groupDto.getGroupIds()
        );

        postUpdate(groupDto, GroupEventUpdate.GroupStatus.UPDATED);

    }


    public void delete(long id){
        GroupDto group = groupReadService.readById(id, true);
        groupRepository.deleteById(id);
        postUpdate(group, GroupEventUpdate.GroupStatus.DELETED);
    }



    private void groupCollective(Group group, Set<Long> groupIds){

        if(!group.isCollective()){
            return;
        }

        //TODO: Think about it
        if(group.getGroups().size() != groupIds.size()) {
            Set<Group> groups = groupReadService.readGroups(groupIds);
            group.getGroups().addAll(groups);
        }
    }


    public void postUpdate(GroupDto groupDto, GroupEventUpdate.GroupStatus status){
        GroupEventUpdate groupEventDto =  GroupTransformer.convertToGroupEvent(groupDto, status);
        streamBridge.send("group-topic", groupEventDto);
    }

}
