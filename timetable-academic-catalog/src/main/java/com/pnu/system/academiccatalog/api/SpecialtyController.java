package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.SpecialtyCreateDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyUpdateDto;
import com.pnu.system.academiccatalog.service.SpecialtySearchService;
import com.pnu.system.academiccatalog.service.SpecialtyService;
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
@RequestMapping("/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService service;
    private final SpecialtySearchService searchService;

    @PostMapping
    public SpecialtyResponseDto create(@Valid @RequestBody SpecialtyCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    public SpecialtyResponseDto update(@Valid @RequestBody SpecialtyUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    public SpecialtyResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    public List<Map<String, Object>> search(@Valid @RequestBody ReportSearchRequest request) {
        return searchService.search(request);
    }
}
