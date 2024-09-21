package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.identityaccess.api.dto.RoleUpdateRequest;
import com.pnu.system.identityaccess.domain.Role;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RoleUpdateValidator extends AbstractRoleValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RoleUpdateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoleUpdateRequest request = (RoleUpdateRequest) target;

        validatePermissions(request.getPermissionIds(), request.getType(), errors);
        validateName(request, errors);
    }

    private void validateName(RoleUpdateRequest request, Errors errors) {
        Role existent = roleService.getOne(request.getId());
        if (ObjectUtils.notEqual(request.getName(), existent.getName())) {
            validateName(request.getName(), errors);
        }
    }
}
