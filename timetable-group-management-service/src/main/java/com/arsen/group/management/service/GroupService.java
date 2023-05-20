package com.arsen.group.management.service;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.group.management.domain.GroupRead;
import com.arsen.group.management.dto.GroupDto;
import com.arsen.group.management.event.GroupEventUpdate;
import com.arsen.group.management.repository.GroupReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupReadRepository groupReadRepository;

    public GroupRead readById(long id){
        return groupReadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GroupRead with id " + id + " is not found!"));
    }

    public GroupRead readById(long id, boolean withGroups){
        return groupReadRepository.findGroupReadWithRelatedGroups(id)
                .orElseThrow(() -> new EntityNotFoundException("GroupRead with id " + id + " is not found!"));
    }


    /**
     * @param groupIds set of ids
     * @return Set of {@link GroupRead}
     */
    public Set<GroupRead> readGroups(Set<Long> groupIds){
        return groupReadRepository.findAllByIdIn(groupIds);
    }

    public void synchronizeWithGroups(GroupEventUpdate groupEventUpdate){
        switch(groupEventUpdate.getStatus()){
            case CREATED -> create(groupEventUpdate);
            case UPDATED -> update(groupEventUpdate);
            case DELETED -> delete(groupEventUpdate.getId());
        }
    }

    /**
     * INTERNAL Operation
     * @param groupAddDto {@link GroupDto}
     */
    public void create(GroupDto groupAddDto){

        if(groupAddDto == null){
            throw new EntityNullReferenceException("Group cannot be null!");
        }

        GroupRead groupRead = new GroupRead();
        groupRead.setId(groupAddDto.getId());
        groupRead.setFullName(groupAddDto.getFullName());
        groupRead.setCollective(groupAddDto.isCollective());

        groupCollective(groupRead, groupAddDto.getGroupIds());
        groupReadRepository.save(groupRead);

    }


    /**
     * INTERNAL Operation
     * @param groupDto {@link GroupDto}
     */
    public void update(GroupDto groupDto){

        if(groupDto == null){
            throw new EntityNullReferenceException("Group cannot be null!");
        }

        GroupRead groupRead = readById(groupDto.getId(), true);
        groupRead.setFullName(groupDto.getFullName());
        groupCollective(groupRead, groupDto.getGroupIds());

        groupReadRepository.save(groupRead);

    }


    /**
     * INTERNAL Operation
     * @param id of group
     */
    public void delete(long id){
        groupReadRepository.deleteById(id);
    }



    private void groupCollective(GroupRead groupRead, Set<Long> ids){

        if(!groupRead.isCollective()){
            return;
        }

        if(ids != null && (groupRead.getGroups() != null)){
            groupRead.resolveGroups(readGroups(ids));
        }
    }

}
