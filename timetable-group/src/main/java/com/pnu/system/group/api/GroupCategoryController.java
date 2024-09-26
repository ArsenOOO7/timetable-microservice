package com.pnu.system.group.api;

import com.pnu.system.group.api.dto.GroupCategoryCreateRequest;
import com.pnu.system.group.api.dto.GroupCategoryResponseDto;
import com.pnu.system.group.api.dto.GroupCategoryUpdateRequest;
import com.pnu.system.group.service.GroupCategoryService;
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
@RequestMapping("/group/category")
@RequiredArgsConstructor
public class GroupCategoryController {

    private final GroupCategoryService service;

    @PostMapping
    public GroupCategoryResponseDto create(@Valid @RequestBody GroupCategoryCreateRequest request) {
        return service.create(request);
    }

    @PutMapping
    public GroupCategoryResponseDto update(@Valid @RequestBody GroupCategoryUpdateRequest request) {
        return service.update(request);
    }

    @GetMapping("/list")
    public List<GroupCategoryResponseDto> getList() {
        return service.getList();
    }

    @DeleteMapping("/{guid}")
    public void delete(@PathVariable String guid) {
        service.delete(guid);
    }
}
