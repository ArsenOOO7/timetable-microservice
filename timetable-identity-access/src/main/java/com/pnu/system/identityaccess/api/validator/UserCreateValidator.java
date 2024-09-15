package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserCreateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateRequest request = (UserCreateRequest) target;
        //validate @JJerome
    }
}
