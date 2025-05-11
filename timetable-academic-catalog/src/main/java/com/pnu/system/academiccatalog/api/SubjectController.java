package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.SubjectCreateDto;
import com.pnu.system.academiccatalog.api.dto.SubjectResponseDto;
import com.pnu.system.academiccatalog.api.dto.SubjectUpdateDto;
import com.pnu.system.academiccatalog.service.SubjectSearchService;
import com.pnu.system.academiccatalog.service.SubjectService;
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
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService service;
    private final SubjectSearchService searchService;

    @PostMapping
    public SubjectResponseDto create(@Valid @RequestBody SubjectCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    public SubjectResponseDto update(@Valid @RequestBody SubjectUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    public SubjectResponseDto getById(@PathVariable String id) {
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
