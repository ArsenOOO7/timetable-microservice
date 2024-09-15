package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserUpdateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserUpdateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserUpdateRequest request = (UserUpdateRequest) target;
        //todo @JJerome
    }
}
