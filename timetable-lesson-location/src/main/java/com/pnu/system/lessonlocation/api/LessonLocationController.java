package com.pnu.system.lessonlocation.api;


import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationUpdateRequest;
import com.pnu.system.lessonlocation.service.LessonLocationService;
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
@RequestMapping("/location")
@RequiredArgsConstructor
public class LessonLocationController {

    private final LessonLocationService lessonLocationService;

    @PostMapping
    private LessonLocationResponseDto createLocation(@Valid @RequestBody LessonLocationCreateRequest createRequest) {
        return lessonLocationService.create(createRequest);
    }

    @PutMapping
    private LessonLocationResponseDto updateLocation(@Valid @RequestBody LessonLocationUpdateRequest updateRequest) {
        return lessonLocationService.update(updateRequest);
    }

    @GetMapping("/{id}")
    private LessonLocationResponseDto getById(@PathVariable String id) {
        return lessonLocationService.getById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteLocation(@PathVariable String id) {
        lessonLocationService.delete(id);
    }

    @PostMapping("/list")
    private List<LessonLocationResponseDto> getAll(@Valid @RequestBody BaseSearchRequest request) {
        return lessonLocationService.getAll(request);
    }
}
