package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainCreateDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainUpdateDto;
import com.pnu.system.academiccatalog.service.KnowledgeDomainService;
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
@RequestMapping("/knowledgeDomain")
@RequiredArgsConstructor
public class KnowledgeDomainController {

    private final KnowledgeDomainService service;

    @PostMapping("/list")
    private List<KnowledgeDomainResponseDto> getAll(@RequestBody BaseSearchRequest request) {
        return service.getAll(request);
    }

    @PostMapping
    private KnowledgeDomainResponseDto create(@Valid @RequestBody KnowledgeDomainCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    private KnowledgeDomainResponseDto update(@Valid @RequestBody KnowledgeDomainUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    private KnowledgeDomainResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }
}
