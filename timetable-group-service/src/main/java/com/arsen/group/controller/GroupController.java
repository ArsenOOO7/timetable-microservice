package com.arsen.group.controller;

import com.arsen.common.dto.SearchDto;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.dto.GroupResultSearchDto;
import com.arsen.group.service.GroupCommandService;
import com.arsen.group.service.GroupReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<GroupDto> create(@RequestBody GroupDto groupDto){
        return ResponseEntity.status(CREATED).body(groupCommandService.create(groupDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> read(@PathVariable long id){
        return ResponseEntity.ok(groupReadService.readById(id, true));
    }


    @PostMapping("/search")
    public ResponseEntity<List<GroupResultSearchDto>> search(@RequestBody SearchDto searchDto){
        return ResponseEntity.ok(groupReadService.searchGroupByQuery(searchDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable long id, @RequestBody GroupDto groupDto){
        groupCommandService.update(groupDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable long id){
        groupCommandService.delete(id);
    }

}
