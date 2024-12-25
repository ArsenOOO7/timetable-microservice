package com.pnu.system.identityaccess.api;

import com.pnu.system.common.search.dto.BaseSearchRequest;
import com.pnu.system.identityaccess.api.dto.RoleCreateRequest;
import com.pnu.system.identityaccess.api.dto.RolePreviewDto;
import com.pnu.system.identityaccess.api.dto.RoleResponseDto;
import com.pnu.system.identityaccess.api.dto.RoleUpdateRequest;
import com.pnu.system.identityaccess.api.validator.RoleCreateValidator;
import com.pnu.system.identityaccess.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final RoleCreateValidator roleCreateValidator;

    @InitBinder("roleCreateRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(roleCreateValidator);
    }

    @PostMapping
    public RoleResponseDto create(@Valid @RequestBody RoleCreateRequest roleCreateRequest) {
        return roleService.create(roleCreateRequest);
    }

    @PutMapping
    public RoleResponseDto update(@Valid @RequestBody RoleUpdateRequest roleUpdateRequest) {
        return roleService.update(roleUpdateRequest);
    }

    @GetMapping("/{id}")
    public RoleResponseDto getById(@PathVariable String id) {
        return roleService.getById(id);
    }

    @PostMapping("/list")
    public List<RolePreviewDto> getList(@Valid @RequestBody BaseSearchRequest request) {
        return roleService.getList(request);
    }
}
