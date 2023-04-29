package com.arsen.group.management.controller;

import com.arsen.group.management.dto.GroupLessonDto;
import com.arsen.group.management.dto.GroupLessonsRequestDto;
import com.arsen.group.management.dto.MultipleLessonGroupResponseDto;
import com.arsen.group.management.service.GroupLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/group/management")
@RequiredArgsConstructor
public class GroupLessonController {

    private final GroupLessonService groupLessonService;


    @GetMapping("/lesson/{lesson}")
    public ResponseEntity<MultipleLessonGroupResponseDto> readLesson(@PathVariable long lesson){
        return ResponseEntity.ok(groupLessonService.readLessonWithGroups(lesson));
    }

    @PostMapping("/lesson")
    public ResponseEntity<List<MultipleLessonGroupResponseDto>> readLessonWithIdRange(@RequestBody Set<Long> ids){
        return ResponseEntity.ok(groupLessonService.readLessonWithGroups(ids));
    }

    @PostMapping("/group/{groupId}")
    public ResponseEntity<List<MultipleLessonGroupResponseDto>> readGroup(@PathVariable long groupId, @RequestBody GroupLessonsRequestDto requestDto){
        return ResponseEntity.ok(groupLessonService.readGroupWithLessonsMultiple(requestDto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody GroupLessonDto groupLessonDto){
        groupLessonService.create(groupLessonDto);
    }


    @DeleteMapping("/{lesson}/{group}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long lesson, @PathVariable long group){
        groupLessonService.delete(lesson, group);
    }

}
