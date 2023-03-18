package com.arsen.classroom.controller;

import com.arsen.classroom.dto.ClassroomDto;
import com.arsen.classroom.service.ClassroomService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDto> readClassroom(@PathVariable long id) {
        return ResponseEntity.ok(classroomService.readById(id, true));
    }


    @PostMapping
    public ResponseEntity<ClassroomDto> create(@RequestBody ClassroomDto classroomDto) {
        return ResponseEntity.status(CREATED).body(classroomService.create(classroomDto));
    }


    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable long id, @RequestBody ClassroomDto classroomDto) {
        classroomService.update(classroomDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        classroomService.delete(id);
    }
}
