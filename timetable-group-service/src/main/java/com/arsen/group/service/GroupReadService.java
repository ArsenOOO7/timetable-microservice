package com.arsen.group.service;

import com.arsen.common.dto.SearchDto;
import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupResponseDto;
import com.arsen.group.dto.GroupResultSearchDto;
import com.arsen.group.mapper.GroupMapper;
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
    private final GroupElasticService groupElasticService;
    private final GroupMapper mapper;


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
     * @return {@link GroupResponseDto}
     */
    public GroupResponseDto readById(long id, boolean dto){
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
     * Returns Group with Groups (related)
     * @param id long
     * @return {@link Group}
     */
    public GroupResponseDto readByIdWithGroups(long id, boolean dto){
        return mapper.toDto(readByIdWithGroups(id));
    }


    /**
     * @param searchDto {@link SearchDto}
     * @return list of {@link GroupResultSearchDto}
     */
    public List<GroupResultSearchDto> searchGroupByQuery(SearchDto searchDto){
        return groupElasticService.searchGroupsByQuery(searchDto);
    }


    /**
     * @param groupIds set of ids
     * @return Set of {@link Group}
     */
    public Set<Group> readGroups(Set<Long> groupIds){
        return groupRepository.findAllByIdIn(groupIds);
    }

}
