package com.arsen.group.service;

import com.arsen.common.event.EntityStatus;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.dto.GroupResponseDto;
import com.arsen.group.mapper.GroupMapper;
import com.arsen.group.repository.GroupRepository;
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
    private final GroupMapper mapper;

    public GroupResponseDto create(GroupDto groupDto){

        if(groupDto == null){
            throw new EntityNullReferenceException("Group cannot be null!");
        }

        Group group = mapper.fromDto(groupDto);
        groupCollective(group, groupDto.getGroupIds());
        group = groupRepository.save(group);

        postUpdate(group, EntityStatus.CREATED);

        return mapper.toDto(group);
    }

    public void update(long id, GroupDto groupDto){

        if(groupDto == null){
            throw new EntityNullReferenceException("Group cannot be null!");
        }

        Group group = groupReadService.readByIdWithGroups(id);
        group = mapper.update(groupDto, group);
        groupCollective(group, groupDto.getGroupIds());

        group = groupRepository.save(group);
        postUpdate(group, EntityStatus.UPDATED);

    }


    public void delete(long id){
        Group group = groupReadService.readById(id);
        groupRepository.deleteById(id);
        postUpdate(group, EntityStatus.DELETED);
    }



    private void groupCollective(Group group, Set<Long> groupIds){
        if(!group.isCollective()){
            return;
        }
        group.addGroups(groupReadService.readGroups(groupIds));
    }


    public void postUpdate(Group group, EntityStatus status){
        streamBridge.send("group-topic", mapper.toEvent(group, status));
    }

}
