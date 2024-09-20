package com.pnu.system.identityaccess.api;

import com.pnu.system.common.constant.RoleType;
import com.pnu.system.identityaccess.domain.Permission;
import com.pnu.system.identityaccess.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService service;

    @GetMapping("/list")
    public List<Permission> getByType(@RequestParam RoleType type) {
        return service.getByType(type);
    }
}
