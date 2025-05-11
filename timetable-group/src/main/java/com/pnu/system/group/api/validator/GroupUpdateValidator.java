package com.pnu.system.group.api.validator;

import com.pnu.system.group.api.dto.GroupUpdateRequest;
import com.pnu.system.group.constant.GroupType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class GroupUpdateValidator extends AbstractGroupValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GroupUpdateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GroupUpdateRequest request = (GroupUpdateRequest) target;

        GroupType type = request.getType();
        validateName(request.getName(), request.getId(), errors);
        validateAcademicYear(type, request.getAcademicYear(), errors);
        validateSpecialtyId(type, request.getSpecialtyId(), errors);
        validateParentGroup(type, request.getParentId(), errors);
        validateRelatedGroups(type, request.getRelatedGroupIds(), errors);
    }
}
