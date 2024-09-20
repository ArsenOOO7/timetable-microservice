package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.identityaccess.api.dto.RoleCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RoleCreateValidator extends AbstractRoleValidator {


    @Override
    public boolean supports(Class<?> clazz) {
        return RoleCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoleCreateRequest request = (RoleCreateRequest) target;

        validatePermissions(request.getPermissionIds(), request.getType(), errors);
        validateName(request.getName(), errors);
    }
}
