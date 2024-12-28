package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainCreateDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainResponseDto;
import com.pnu.system.academiccatalog.api.dto.KnowledgeDomainUpdateDto;
import com.pnu.system.academiccatalog.service.KnowledgeDomainSearchService;
import com.pnu.system.academiccatalog.service.KnowledgeDomainService;
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
@RequestMapping("/knowledgeDomain")
@RequiredArgsConstructor
public class KnowledgeDomainController {

    private final KnowledgeDomainService service;
    private final KnowledgeDomainSearchService searchService;

    @PostMapping
    public KnowledgeDomainResponseDto create(@Valid @RequestBody KnowledgeDomainCreateDto createDto) {
        return service.create(createDto);
    }

    @PutMapping
    public KnowledgeDomainResponseDto update(@Valid @RequestBody KnowledgeDomainUpdateDto updateDto) {
        return service.update(updateDto);
    }

    @GetMapping("/{id}")
    public KnowledgeDomainResponseDto getById(@PathVariable String id) {
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
