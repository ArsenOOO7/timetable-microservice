package com.arsen.classroom.controller;

import com.arsen.classroom.dto.ClassroomDto;
import com.arsen.classroom.dto.ClassroomResponseDto;
import com.arsen.classroom.service.ClassroomService;
import com.arsen.common.dto.SearchDto;
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
@RequestMapping("/api/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResponseDto> readClassroom(@PathVariable long id) {
        return ResponseEntity.ok(classroomService.readById(id, true));
    }


    @PostMapping
    public ResponseEntity<ClassroomResponseDto> create(@RequestBody ClassroomDto classroomDto) {
        return ResponseEntity.status(CREATED).body(classroomService.create(classroomDto));
    }

    @PostMapping("/search")
    public ResponseEntity<List<ClassroomResponseDto>> search(@RequestBody SearchDto dto){
        return ResponseEntity.ok(classroomService.search(dto));
    }


    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable long id, @RequestBody ClassroomDto classroomDto) {
        classroomService.update(id, classroomDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        classroomService.delete(id);
    }
}
