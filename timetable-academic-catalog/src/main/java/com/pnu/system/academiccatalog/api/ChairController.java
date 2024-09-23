package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.ChairCreateDto;
import com.pnu.system.academiccatalog.api.dto.ChairResponseDto;
import com.pnu.system.academiccatalog.api.dto.ChairUpdateDto;
import com.pnu.system.academiccatalog.service.ChairService;
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
@RequestMapping("/chair")
@RequiredArgsConstructor
public class ChairController {

    private final ChairService service;

    @PostMapping("/list")
    private List<ChairResponseDto> getAll(@RequestBody BaseSearchRequest searchRequest) {
        return service.getAll(searchRequest);
    }

    @PostMapping
    private ChairResponseDto create(@Valid @RequestBody ChairCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    private ChairResponseDto update(@Valid @RequestBody ChairUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    private ChairResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }
}
