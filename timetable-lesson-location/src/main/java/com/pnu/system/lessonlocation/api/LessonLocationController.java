package com.pnu.system.lessonlocation.api;


import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationCreateRequest;
import com.pnu.system.lessonlocation.api.dto.LessonLocationResponseDto;
import com.pnu.system.lessonlocation.api.dto.LessonLocationUpdateRequest;
import com.pnu.system.lessonlocation.service.LessonLocationSearchService;
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
import java.util.Map;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LessonLocationController {

    private final LessonLocationService service;
    private final LessonLocationSearchService searchService;

    @PostMapping
    private LessonLocationResponseDto createLocation(@Valid @RequestBody LessonLocationCreateRequest createRequest) {
        return service.create(createRequest);
    }

    @PutMapping
    private LessonLocationResponseDto updateLocation(@Valid @RequestBody LessonLocationUpdateRequest updateRequest) {
        return service.update(updateRequest);
    }

    @GetMapping("/{id}")
    private LessonLocationResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteLocation(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/list")
    public List<Map<String, Object>> test(@Valid @RequestBody ReportSearchRequest request) {
        return searchService.search(request);
    }
}
