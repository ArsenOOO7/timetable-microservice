package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.EducationalProgramCreateDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramPreviewDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramResponseDto;
import com.pnu.system.academiccatalog.api.dto.EducationalProgramUpdateDto;
import com.pnu.system.academiccatalog.service.EducationalProgramService;
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
@RequestMapping("/educationalProgram")
@RequiredArgsConstructor
public class EducationalProgramController {

    private final EducationalProgramService service;

    @PostMapping("/list")
    private List<EducationalProgramPreviewDto> getAll(@Valid @RequestBody BaseSearchRequest request) {
        return service.getAll(request);
    }

    @PostMapping
    private EducationalProgramResponseDto create(@Valid @RequestBody EducationalProgramCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    private EducationalProgramResponseDto update(@Valid @RequestBody EducationalProgramUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    private EducationalProgramResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable String id) {
        service.delete(id);
    }

}
