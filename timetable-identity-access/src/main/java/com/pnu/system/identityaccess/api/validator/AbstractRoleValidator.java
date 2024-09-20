package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.common.constant.RoleType;
import com.pnu.system.identityaccess.service.PermissionService;
import com.pnu.system.identityaccess.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public abstract class AbstractRoleValidator implements Validator {

    @Autowired
    protected RoleService roleService;
    @Autowired
    protected PermissionService permissionService;

    protected void validatePermissions(List<String> permissionIds, RoleType type, Errors errors) {
        permissionService.getAll(permissionIds)
                .forEach(permission -> {
                    if (permission.getType().equals(type)) {
                        return;
                    }
                    //TODO: Add error code
                    errors.reject("", "Insufficient permission %s (%s) type.".formatted(permission.getId(), permission.getType()));
                });
    }

    protected void validateName(String name, Errors errors) {
        if (roleService.existsByName(name)) {
            errors.reject("", "Role with such name already exists.");
        }
    }
}
