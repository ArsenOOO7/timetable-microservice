package com.pnu.system.lessonlocation.api;

import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationTypeUpdateDto;
import com.pnu.system.lessonlocation.service.LocationTypeService;
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
@RequestMapping("/location/type")
@RequiredArgsConstructor
public class LessonLocationTypeController {

    private final LocationTypeService locationTypeService;

    @PostMapping
    public LessonLocationTypeResponseDto create(@Valid @RequestBody LessonLocationTypeCreateRequest lessonLocationTypeCreateRequest) {
        return locationTypeService.create(lessonLocationTypeCreateRequest);
    }

    @PutMapping
    public LessonLocationTypeResponseDto update(@Valid @RequestBody LessonLocationTypeUpdateDto lessonLocationTypeUpdateDto) {
        return locationTypeService.update(lessonLocationTypeUpdateDto);
    }

    @GetMapping("/{id}")
    public LessonLocationTypeResponseDto getById(@PathVariable String id) {
        return locationTypeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        locationTypeService.delete(id);
    }

    @GetMapping("/list")
    public List<LessonLocationTypeResponseDto> getAll() {
        return locationTypeService.getAll();
    }
}
