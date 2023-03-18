package com.arsen.group.controller;

import com.arsen.group.dto.GroupDto;
import com.arsen.group.dto.GroupQuerySearchDto;
import com.arsen.group.dto.GroupResultSearchDto;
import com.arsen.group.service.GroupReadService;
import com.arsen.group.service.GroupCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<GroupResultSearchDto>> search(@RequestBody GroupQuerySearchDto searchDto){
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
