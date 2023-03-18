package com.arsen.teacher.controller;

import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherQueryDto;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import com.arsen.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> read(@PathVariable long id){
        return ResponseEntity.ok(teacherService.readById(id, true));
    }

    @PostMapping("/search")
    public ResponseEntity<List<TeacherResultSearchDto>> findQuery(@RequestBody TeacherQueryDto teacherQueryDto){
        return ResponseEntity.ok(teacherService.findAllByQuery(teacherQueryDto));
    }

    @PostMapping
    public ResponseEntity<TeacherDto> create(/*@Valid*/ @RequestBody TeacherDto teacherDto){
        return ResponseEntity.status(CREATED).body(teacherService.create(teacherDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable long id, /*@Valid*/ @RequestBody TeacherDto teacherDto){
        teacherService.update(teacherDto);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable long id){
        teacherService.delete(id);
    }

}
