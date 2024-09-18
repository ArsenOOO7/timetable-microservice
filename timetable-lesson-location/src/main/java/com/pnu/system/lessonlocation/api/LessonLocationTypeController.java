package com.pnu.system.lessonlocation.api;

import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeCreateDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeUpdateDto;
import com.pnu.system.lessonlocation.service.LocationTypeService;
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
@RequestMapping("/location/type")
@RequiredArgsConstructor
public class LessonLocationTypeController {

    private final LocationTypeService locationTypeService;

    @PostMapping
    public LessonLocationTypeDto create(@RequestBody LessonLocationTypeCreateDto lessonLocationTypeCreateDto) {
        return locationTypeService.create(lessonLocationTypeCreateDto);
    }

    @PutMapping
    public LessonLocationTypeDto update(@RequestBody LessonLocationTypeUpdateDto lessonLocationTypeUpdateDto) {
        return locationTypeService.update(lessonLocationTypeUpdateDto);
    }

    @GetMapping("/{id}")
    public LessonLocationTypeDto getById(@PathVariable String id) {
        return locationTypeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        locationTypeService.delete(id);
    }

    @GetMapping("/list")
    public List<LessonLocationTypeDto> getAll() {
        return locationTypeService.getAll();
    }
}
