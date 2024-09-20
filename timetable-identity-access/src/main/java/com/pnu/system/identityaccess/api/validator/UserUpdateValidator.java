package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.domain.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserUpdateValidator extends AbstractUserValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserUpdateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserUpdateRequest request = (UserUpdateRequest) target;
        validateUserType(request.getType(), request.getRoleIds(), errors);
        validateEmail(request, errors);
    }

    private void validateEmail(UserUpdateRequest request, Errors errors) {
        User existentUser = userService.getOne(request.getId());
        if (ObjectUtils.notEqual(request.getEmail(), existentUser.getEmail())) {
            validateEmail(request.getEmail(), errors);
        }
    }
}
