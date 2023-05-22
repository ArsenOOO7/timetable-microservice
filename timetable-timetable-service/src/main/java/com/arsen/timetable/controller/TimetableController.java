package com.arsen.timetable.controller;

import com.arsen.timetable.dto.LessonCreatedBrieflyResponseDto;
import com.arsen.timetable.dto.LessonDto;
import com.arsen.timetable.dto.LessonSearchDto;
import com.arsen.timetable.dto.TimetableResponseDto;
import com.arsen.timetable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final LessonService lessonService;

    @PostMapping("/search")
    public ResponseEntity<List<TimetableResponseDto>> read(@RequestBody LessonSearchDto dto){
        if(dto.getTeacherId() != null){
            return ResponseEntity.ok(lessonService.readByTeacher(dto));
        }

        if(dto.getGroupId() != null){
            return ResponseEntity.ok(lessonService.readByGroup(dto));
        }

        return ResponseEntity.internalServerError().build();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public ResponseEntity<LessonCreatedBrieflyResponseDto> create(@RequestBody LessonDto lessonDto){
        return ResponseEntity.status(CREATED).body(lessonService.create(lessonDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public void update(@PathVariable long id, @RequestBody LessonDto lessonDto){
        lessonService.update(id, lessonDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public void delete(@PathVariable long id){
        lessonService.delete(id);
    }

    @DeleteMapping("/{id}/{groupId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_UNIT_MANAGER')")
    public void delete(@PathVariable long id, @PathVariable long groupId){
        lessonService.delete(id, groupId);
    }

}
