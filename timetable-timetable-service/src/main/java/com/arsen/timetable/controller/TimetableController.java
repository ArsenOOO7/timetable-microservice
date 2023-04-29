package com.arsen.timetable.controller;

import com.arsen.timetable.dto.LessonCreatedBrieflyResponseDto;
import com.arsen.timetable.dto.LessonDto;
import com.arsen.timetable.dto.LessonSearchDto;
import com.arsen.timetable.dto.TimetableResponseDto;
import com.arsen.timetable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LessonCreatedBrieflyResponseDto> create(@RequestBody LessonDto lessonDto){
        return ResponseEntity.status(CREATED).body(lessonService.create(lessonDto));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody LessonDto lessonDto){
        lessonService.update(id, lessonDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        lessonService.delete(id);
    }

}
