package com.arsen.group.controller;

import com.arsen.common.dto.SearchDto;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.dto.GroupResponseDto;
import com.arsen.group.dto.GroupResultSearchDto;
import com.arsen.group.service.GroupCommandService;
import com.arsen.group.service.GroupReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupCommandService groupCommandService;
    private final GroupReadService groupReadService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public ResponseEntity<GroupResponseDto> create(@RequestBody GroupDto groupDto){
        return ResponseEntity.status(CREATED).body(groupCommandService.create(groupDto));
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public ResponseEntity<GroupResponseDto> read(@PathVariable long id){
        return ResponseEntity.ok(groupReadService.readByIdWithGroups(id, true));
    }


    @PostMapping("/search")
    public ResponseEntity<List<GroupResultSearchDto>> search(@RequestBody SearchDto searchDto){
        return ResponseEntity.ok(groupReadService.searchGroupByQuery(searchDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public void update(@PathVariable long id, @RequestBody GroupDto groupDto){
        groupCommandService.update(id, groupDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public void delete(@PathVariable long id){
        groupCommandService.delete(id);
    }

    @PatchMapping("/{id}/{groupId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public void addGroup(@PathVariable long id, @PathVariable long groupId){
        groupCommandService.addGroup(id, groupId);
    }

    @DeleteMapping("/{id}/{groupId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public void removeGroup(@PathVariable long id, @PathVariable long groupId){
        groupCommandService.removeGroup(id, groupId);
    }

}
