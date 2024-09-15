package com.pnu.system.identityaccess.api.validator;

import com.pnu.system.identityaccess.api.dto.RoleCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleCreateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RoleCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoleCreateRequest request = (RoleCreateRequest) target;
        //validate
    }
}
