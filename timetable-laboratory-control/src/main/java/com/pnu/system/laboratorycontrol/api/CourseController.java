package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.common.dto.UserDto;
import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.laboratorycontrol.api.dto.CourseCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.CourseDto;
import com.pnu.system.laboratorycontrol.api.dto.CourseGroupDto;
import com.pnu.system.laboratorycontrol.api.dto.CourseUpdateRequest;
import com.pnu.system.laboratorycontrol.service.CourseSearchService;
import com.pnu.system.laboratorycontrol.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;
    private final CourseSearchService searchService;

    @PostMapping
    public CourseDto create(@Valid @RequestBody CourseCreateRequest request) {
        return service.create(request);
    }

    @PutMapping
    public CourseDto update(@Valid @RequestBody CourseUpdateRequest request) {
        return service.update(request);
    }

    @GetMapping("/{id}")
    public CourseDto getOne(@PathVariable String id) {
        return service.getOneById(id);
    }

    @PostMapping("/{id}/author")
    public void addAuthor(@PathVariable String id, @RequestParam String userId) {
        service.addAuthor(id, userId);
    }

    @DeleteMapping("/{id}/author")
    public void removeAuthor(@PathVariable String id, @RequestParam String userId) {
        service.removeAuthor(id, userId);
    }

    @PutMapping("/{id}/archive")
    public void archive(@PathVariable String id) {
        service.archive(id);
    }

    @PutMapping("/{id}/makeActive")
    public void makeActive(@PathVariable String id) {
        service.makeActive(id);
    }

    @PostMapping("/list")
    public List<Map<String, Object>> getList(@Valid @RequestBody ReportSearchRequest request) {
        return searchService.search(request);
    }

    @PostMapping("/{id}/group")
    public void addGroup(@PathVariable String id, @RequestParam String groupId) {
        service.addGroup(id, groupId);
    }

    @DeleteMapping("/{id}/group")
    public void removeGroup(@PathVariable String id, @RequestParam String groupId) {
        service.removeGroup(id, groupId);
    }

    @GetMapping("/{id}/authors")
    public List<UserDto> getAuthors(@PathVariable String id) {
        return service.getAuthors(id);
    }

    @GetMapping("/{id}/groups")
    public List<CourseGroupDto> getGroups(@PathVariable String id) {
        return service.getGroups(id);
    }
}
