package com.pnu.system.group.api;

import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.group.api.dto.GroupCreateRequest;
import com.pnu.system.group.api.dto.GroupResponseDto;
import com.pnu.system.group.api.dto.GroupUpdateRequest;
import com.pnu.system.group.api.validator.GroupCreateValidator;
import com.pnu.system.group.api.validator.GroupUpdateValidator;
import com.pnu.system.group.service.GroupSearchService;
import com.pnu.system.group.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService service;
    private final GroupSearchService searchService;
    private final GroupCreateValidator groupCreateValidator;
    private final GroupUpdateValidator groupUpdateValidator;

    @InitBinder("groupCreateRequest")
    protected void initGroupCreateBinder(WebDataBinder binder) {
        binder.addValidators(groupCreateValidator);
    }

    @InitBinder("groupUpdateRequest")
    protected void initGroupUpdateBinder(WebDataBinder binder) {
        binder.addValidators(groupUpdateValidator);
    }

    @PostMapping
    public GroupResponseDto create(@Valid @RequestBody GroupCreateRequest groupCreateRequest) {
        return service.create(groupCreateRequest);
    }

    @PutMapping
    public GroupResponseDto update(@Valid @RequestBody GroupUpdateRequest groupUpdateRequest) {
        return service.update(groupUpdateRequest);
    }

    @PostMapping("/list")
    public List<Map<String, Object>> search(@Valid @RequestBody ReportSearchRequest request) {
        return searchService.search(request);
    }

    @DeleteMapping("/{guid}")
    public void delete(@PathVariable String guid) {
        service.delete(guid);
    }
}
