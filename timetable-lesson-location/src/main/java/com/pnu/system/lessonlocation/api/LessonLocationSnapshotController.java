package com.pnu.system.lessonlocation.api;

import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.lessonlocation.service.LessonLocationSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location/internal/snapshot")
@RequiredArgsConstructor
public class LessonLocationSnapshotController {

    private final LessonLocationSnapshotService service;

    @GetMapping("/{id}")
    public LessonLocationSnapshotDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping("/list")
    public List<LessonLocationSnapshotDto> getByIds(@RequestBody List<String> ids) {
        return service.getByIds(ids);
    }
}
