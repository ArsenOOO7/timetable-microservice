package com.arsen.group.service;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.dto.GroupQuerySearchDto;
import com.arsen.group.dto.GroupResultSearchDto;
import com.arsen.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 *
 * Only-Read service
 *
 * @author Arsen Sydoryk
 */
@Service
@RequiredArgsConstructor
public class GroupReadService {

    private final GroupRepository groupRepository;


    /**
     * Returns simple Group entity
     * @param id of entity
     * @return {@link Group}
     */
    public Group readById(long id){
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " is not found!!"));
    }


    /**
     * Returns GroupDto
     * @param id of entity
     * @param dto
     * @return {@link GroupDto}
     */
    public GroupDto readById(long id, boolean dto){
        return groupRepository.readByIdDto(id)
                .orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " is not found!!"));
    }


    /**
     * Returns Group with Groups (related)
     * @param id long
     * @return {@link Group}
     */
    public Group readByIdWithGroups(long id){
        return groupRepository.getGroupWithGroups(id)
                .orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " is not found!!"));
    }


    /**
     * @param searchDto {@link GroupQuerySearchDto}
     * @return {@link GroupResultSearchDto}
     */
    public List<GroupResultSearchDto> searchGroupByQuery(GroupQuerySearchDto searchDto){
        return groupRepository.findQueryGroup(searchDto.getGroupQuery());
    }


    /**
     * @param groupIds set of ids
     * @return Set of {@link Group}
     */
    public Set<Group> readGroups(Set<Long> groupIds){
        return groupRepository.findAllByIdIn(groupIds);
    }

}