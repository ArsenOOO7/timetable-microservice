package com.pnu.system.timetable.api;

import com.pnu.system.timetable.api.dto.LessonTypeCreateRequest;
import com.pnu.system.timetable.api.dto.LessonTypeResponseDto;
import com.pnu.system.timetable.api.dto.LessonTypeUpdateRequest;
import com.pnu.system.timetable.service.LessonTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lesson/type")
@RequiredArgsConstructor
public class LessonTypeController {

    private final LessonTypeService service;

    @PostMapping
    public LessonTypeResponseDto create(@Valid @RequestBody LessonTypeCreateRequest request) {
        return service.create(request);
    }

    @PutMapping
    public LessonTypeResponseDto update(@Valid @RequestBody LessonTypeUpdateRequest request) {
        return service.update(request);
    }

    @GetMapping("/list")
    public List<LessonTypeResponseDto> getList() {
        return service.getList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
