package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.SpecialtyCreateDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyResponseDto;
import com.pnu.system.academiccatalog.api.dto.SpecialtyUpdateDto;
import com.pnu.system.academiccatalog.service.SpecialtyService;
import com.pnu.system.common.dto.BaseSearchRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService service;

    @PostMapping("/list")
    private List<SpecialtyResponseDto> getAll(@RequestBody BaseSearchRequest searchRequest) {
        return service.getAll(searchRequest);
    }

    @PostMapping
    private SpecialtyResponseDto create(@Valid @RequestBody SpecialtyCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    private SpecialtyResponseDto update(@Valid @RequestBody SpecialtyUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    private SpecialtyResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }
}
