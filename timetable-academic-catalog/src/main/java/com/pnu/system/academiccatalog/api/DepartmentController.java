package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.DepartmentCreateDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentResponseDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentUpdateDto;
import com.pnu.system.academiccatalog.service.DepartmentSearchService;
import com.pnu.system.academiccatalog.service.DepartmentService;
import com.pnu.system.common.search.dto.ReportSearchRequest;
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
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;
    private final DepartmentSearchService searchService;

    @PostMapping
    public DepartmentResponseDto create(@Valid @RequestBody DepartmentCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    public DepartmentResponseDto update(@Valid @RequestBody DepartmentUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    public DepartmentResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/list")
    public List<Map<String, Object>> search(@Valid @RequestBody ReportSearchRequest request) {
        return searchService.search(request);
    }
}
