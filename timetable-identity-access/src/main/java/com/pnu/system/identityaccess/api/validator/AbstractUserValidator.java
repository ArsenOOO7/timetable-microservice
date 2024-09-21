package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.identityaccess.service.RoleService;
import com.pnu.system.identityaccess.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public abstract class AbstractUserValidator implements Validator {

    @Autowired
    protected UserService userService;
    @Autowired
    protected RoleService roleService;

    protected void validateUserType(UserType userType, List<String> roleIds, Errors errors) {
        roleService.getAll(roleIds)
                .forEach(role -> {
                    if (ObjectUtils.notEqual(userType.roleType(), role.getType())) {
                        errors.reject("", "Insufficient role (%s) with this user type.".formatted(role.getType()));
                    }
                });
    }

    protected void validateEmail(String email, Errors errors) {
        if (userService.existsByEmail(email)) {
            errors.reject("", "Email is used by another user.");
        }
    }
}
