package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserCreateValidator extends AbstractUserValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateRequest request = (UserCreateRequest) target;
        validateUserType(request.getType(), request.getRoleIds(), errors);
    }
}
