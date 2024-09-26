package com.pnu.system.group.api.validator;

import com.pnu.system.group.constant.GroupType;
import com.pnu.system.group.domain.Group;
import com.pnu.system.group.service.GroupCategoryService;
import com.pnu.system.group.service.GroupService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Objects;

public abstract class AbstractGroupValidator implements Validator {

    @Autowired
    protected GroupService groupService;
    @Autowired
    protected GroupCategoryService groupCategoryService;

    protected void validateRelatedGroups(GroupType type, List<String> relatedGroupIds, Errors errors) {
        if (!GroupType.COLLECTIVE.equals(type) && CollectionUtils.isNotEmpty(relatedGroupIds)) {
            errors.reject("", "Non-collective group cannot contain related groups.");
            return;
        }
        List<Group> relatedGroups = groupService.getAll(relatedGroupIds);
        if (relatedGroups.stream().anyMatch(relatedGroup -> !GroupType.FULL.equals(relatedGroup.getType()))) {
            errors.reject("", "In collective groups all related groups must be FULL type.");
        }
    }

    protected void validateParentGroup(GroupType type, String parentId, Errors errors) {
        if (!GroupType.SUB_GROUP.equals(type)) {
            if (StringUtils.isNotBlank(parentId)) {
                errors.reject("", "Non-subgroup cannot have parent group.");
            }
            return;
        }
        if (StringUtils.isBlank(parentId)) {
            errors.reject("", "Subgroup must have parent group.");
            return;
        }
        Group parentGroup = groupService.getOne(parentId);
        if (!GroupType.FULL.equals(parentGroup.getType())) {
            errors.reject("", "Parent group type should be FULL.");
        }
    }

    protected void validateSpecialtyId(GroupType type, String specialtyId, Errors errors) {
        if (!GroupType.COLLECTIVE.equals(type) && StringUtils.isBlank(specialtyId)) {
            errors.reject("", "Specialty is required for non-collective groups.");
        }
    }

    protected void validateAcademicYear(GroupType type, Integer academicYear, Errors errors) {
        if (!GroupType.FULL.equals(type)) {
            return;
        }

        if (Objects.isNull(academicYear)) {
            errors.reject("", "Academic Year should be filled on FULL type.");
            return;
        }

        if (academicYear < 0 || academicYear > 4) {
            errors.reject("", "Academic Year should be between 1 and 4.");
        }
    }
}
