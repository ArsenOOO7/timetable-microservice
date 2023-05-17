package com.arsen.group.service;

import com.arsen.common.dto.SearchDto;
import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.group.domain.Group;
import com.arsen.group.domain.GroupDocument;
import com.arsen.group.dto.GroupResultSearchDto;
import com.arsen.group.mapper.GroupMapper;
import com.arsen.group.repository.GroupElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupElasticService {

    private final GroupElasticRepository groupElasticRepository;
    private final GroupMapper groupMapper;

    public void create(Group group){

        if(group == null){
            throw new EntityNullReferenceException("Group cannot be null!");
        }

        GroupDocument groupDocument = groupMapper.toDocument(group);
        groupElasticRepository.save(groupDocument);

    }


    public void update(Group group){
        if(group == null){
            throw new EntityNullReferenceException("Group cannot be null!");
        }

        GroupDocument groupDocument = groupElasticRepository.findById(group.getId()).orElseThrow(() -> new EntityNotFoundException("Group with id " + group.getId() + " is not found!"));
        groupDocument.setGroupName(group.toString());

        groupElasticRepository.save(groupDocument);
    }


    public void delete(long id){
        groupElasticRepository.deleteById(id);
    }


    public List<GroupResultSearchDto> searchGroupsByQuery(SearchDto searchDto){
        return groupMapper.fromDocuments(groupElasticRepository.findAllByGroupNameLike(searchDto.getSearchQuery()));
    }

}
