package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.laboratorycontrol.api.dto.AssignmentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentDto;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentUpdateRequest;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentSearchRequest;
import com.pnu.system.laboratorycontrol.service.AssignmentSearchService;
import com.pnu.system.laboratorycontrol.service.AssignmentService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    private final AssignmentService service;
    private final AssignmentSearchService searchService;

    @PostMapping
    public AssignmentDto create(@Valid @RequestBody AssignmentCreateRequest request) {
        return service.create(request);
    }

    @PutMapping
    public AssignmentDto update(@Valid @RequestBody AssignmentUpdateRequest request) {
        return service.update(request);
    }

    @GetMapping("/{id}")
    public AssignmentDto getOne(@PathVariable String id) {
        return service.getOneById(id);
    }

    @PostMapping("/list")
    public List<Map<String, Object>> getList(@Valid @RequestBody AssignmentSearchRequest request) {
        return searchService.search(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
