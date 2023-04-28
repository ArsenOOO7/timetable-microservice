package com.arsen.subject.controller;

import com.arsen.common.dto.SearchDto;
import com.arsen.subject.dto.SubjectDto;
import com.arsen.subject.dto.SubjectResponseDto;
import com.arsen.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;


    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> read(@PathVariable long id){
        return ResponseEntity.ok(subjectService.readById(id, true));
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDto> create(/*@Valid*/ @RequestBody SubjectDto subjectDto){
        return ResponseEntity.status(CREATED).body(subjectService.create(subjectDto));
    }

    @PostMapping("/search")
    public ResponseEntity<List<SubjectResponseDto>> search(@RequestBody SearchDto dto){
        return ResponseEntity.ok(subjectService.search(dto));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, /*@Valid*/ @RequestBody SubjectDto subjectDto){
        subjectService.update(id, subjectDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        subjectService.delete(id);
    }

}
