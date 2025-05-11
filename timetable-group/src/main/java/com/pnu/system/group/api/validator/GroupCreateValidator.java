package com.pnu.system.group.api.validator;

import com.pnu.system.group.api.dto.GroupCreateRequest;
import com.pnu.system.group.constant.GroupType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class GroupCreateValidator extends AbstractGroupValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GroupCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GroupCreateRequest request = (GroupCreateRequest) target;

        GroupType type = request.getType();
        validateName(request.getName(), null, errors);
        validateAcademicYear(type, request.getAcademicYear(), errors);
        validateSpecialtyId(type, request.getSpecialtyId(), errors);
        validateParentGroup(type, request.getParentId(), errors);
        validateRelatedGroups(type, request.getRelatedGroupIds(), errors);
    }
}
