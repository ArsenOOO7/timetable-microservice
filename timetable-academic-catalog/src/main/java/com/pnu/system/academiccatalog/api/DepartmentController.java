package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.DepartmentCreateDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentResponseDto;
import com.pnu.system.academiccatalog.api.dto.DepartmentUpdateDto;
import com.pnu.system.academiccatalog.service.DepartmentService;
import com.pnu.system.common.dto.BaseSearchRequest;
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

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;

    @PostMapping("/list")
    private List<DepartmentResponseDto> getAll(@RequestBody BaseSearchRequest searchRequest) {
        return service.getAll(searchRequest);
    }

    @PostMapping
    private DepartmentResponseDto create(@Valid @RequestBody DepartmentCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    private DepartmentResponseDto update(@Valid @RequestBody DepartmentUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    private DepartmentResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable String id) {
        service.delete(id);
    }

}
