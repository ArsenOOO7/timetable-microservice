package com.arsen.teacher.controller;

import com.arsen.common.dto.SearchDto;
import com.arsen.teacher.dto.TeacherDto;
import com.arsen.teacher.dto.TeacherResponseDto;
import com.arsen.teacher.dto.TeacherResultSearchDto;
import com.arsen.teacher.service.TeacherService;
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
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> read(@PathVariable long id){
        return ResponseEntity.ok(teacherService.readById(id, true));
    }

    @PostMapping("/search")
    public ResponseEntity<List<TeacherResultSearchDto>> findQuery(@RequestBody SearchDto searchDto){
        return ResponseEntity.ok(teacherService.findAllByQuery(searchDto));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> create(/*@Valid*/ @RequestBody TeacherDto teacherDto){
        return ResponseEntity.status(CREATED).body(teacherService.create(teacherDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable long id, /*@Valid*/ @RequestBody TeacherDto teacherDto){
        teacherService.update(id, teacherDto);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable long id){
        teacherService.delete(id);
    }

}
