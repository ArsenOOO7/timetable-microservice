package com.arsen.group.management.controller;

import com.arsen.group.management.dto.GroupLessonDto;
import com.arsen.group.management.dto.GroupLessonsRequestDto;
import com.arsen.group.management.dto.GroupLessonsResponseDto;
import com.arsen.group.management.dto.MultipleLessonGroupResponseDto;
import com.arsen.group.management.service.GroupLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group/management")
@RequiredArgsConstructor
public class GroupLessonController {

    private final GroupLessonService groupLessonService;


    @GetMapping("/lesson/{lesson}")
    public ResponseEntity<MultipleLessonGroupResponseDto> readLesson(@PathVariable long lesson){
        return ResponseEntity.ok(groupLessonService.readLessonWithGroups(lesson));
    }

    @PostMapping("/group/{groupId}")
    public ResponseEntity<GroupLessonsResponseDto> readGroup(@PathVariable long groupId, @RequestBody GroupLessonsRequestDto requestDto){
        return ResponseEntity.ok(groupLessonService.readGroupWithLessons(requestDto));
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
