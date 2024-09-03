package com.pnu.system.api;


import com.pnu.system.api.dto.LessonLocationCreateRequest;
import com.pnu.system.domain.LessonLocation;
import com.pnu.system.mapper.LessonLocationMapper;
import com.pnu.system.service.LessonLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LessonLocationController {
    private final LessonLocationService lessonLocationService;
    private final LessonLocationMapper lessonLocationMapper;

    @GetMapping("/list")
    private List<LessonLocation> getLocations() {
        return lessonLocationService.getAllLessonLocation();
    }

    @PostMapping
    private LessonLocation createLocation(@RequestBody LessonLocationCreateRequest createRequest) {
        return lessonLocationService.create(lessonLocationMapper.toLessonLocation(createRequest));
    }
}
