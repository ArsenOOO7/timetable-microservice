package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkDto;
import com.pnu.system.laboratorycontrol.api.dto.CoursePrimaryLinkUpdateRequest;
import com.pnu.system.laboratorycontrol.service.CoursePrimaryLinkService;
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

@RestController
@RequestMapping("/course/primaryLink")
@RequiredArgsConstructor
public class CoursePrimaryLinkController {

    private final CoursePrimaryLinkService service;

    @PostMapping
    public CoursePrimaryLinkDto create(@Valid @RequestBody CoursePrimaryLinkCreateRequest request) {
        return service.create(request);
    }

    @PutMapping
    public CoursePrimaryLinkDto update(@Valid @RequestBody CoursePrimaryLinkUpdateRequest request) {
        return service.update(request);
    }

    @GetMapping
    public List<CoursePrimaryLinkDto> getList(@RequestParam String courseId) {
        return service.getList(courseId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
