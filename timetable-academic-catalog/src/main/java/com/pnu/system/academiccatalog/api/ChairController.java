package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.ChairCreateDto;
import com.pnu.system.academiccatalog.api.dto.ChairResponseDto;
import com.pnu.system.academiccatalog.api.dto.ChairUpdateDto;
import com.pnu.system.academiccatalog.service.ChairSearchService;
import com.pnu.system.academiccatalog.service.ChairService;
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
@RequestMapping("/chair")
@RequiredArgsConstructor
public class ChairController {

    private final ChairService service;
    private final ChairSearchService searchService;

    @PostMapping
    public ChairResponseDto create(@Valid @RequestBody ChairCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    public ChairResponseDto update(@Valid @RequestBody ChairUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    public ChairResponseDto getById(@PathVariable String id) {
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
