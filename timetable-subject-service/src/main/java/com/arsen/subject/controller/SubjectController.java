package com.arsen.subject.controller;

import com.arsen.subject.dto.SubjectDto;
import com.arsen.subject.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;


    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> read(@PathVariable long id){
        return ResponseEntity.ok(subjectService.readById(id, true));
    }

    @PostMapping
    public ResponseEntity<SubjectDto> create(/*@Valid*/ @RequestBody SubjectDto subjectDto){
        return ResponseEntity.status(CREATED).body(subjectService.create(subjectDto));
    }


    @PutMapping("/{id}")
    public void update(@PathVariable long id, /*@Valid*/ @RequestBody SubjectDto subjectDto){
        subjectService.update(subjectDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        subjectService.delete(id);
    }

}
