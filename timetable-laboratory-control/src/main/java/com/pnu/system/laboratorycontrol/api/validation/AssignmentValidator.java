package com.pnu.system.laboratorycontrol.api.validation;

import com.pnu.system.common.exception.ErrorState;
import com.pnu.system.common.exception.ValidationException;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.AssignmentUpdateRequest;
import com.pnu.system.laboratorycontrol.constant.AssignmentVisibility;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;

@Component
public class AssignmentValidator {

    public void validateAssignment(AssignmentCreateRequest request) {
        ErrorState state = new ErrorState();
        validateVisibility(request.getVisibility(), request.getAppearAt(), state);
        validateDeadline(request.getDeadline(), state);
        validateAppearAtDateWithDeadline(request.getAppearAt(), request.getDeadline(), state);

        if (state.hasErrors()) {
            throw new ValidationException("validation.assignment.create.errors", state.getErrors());
        }
    }

    public void validateAssignment(AssignmentUpdateRequest request) {
        ErrorState state = new ErrorState();
        validateVisibility(request.getVisibility(), request.getAppearAt(), state);
        validateDeadline(request.getDeadline(), state);
        validateAppearAtDateWithDeadline(request.getAppearAt(), request.getDeadline(), state);

        if (state.hasErrors()) {
            throw new ValidationException("validation.assignment.update.errors", state.getErrors());
        }
    }

    private void validateVisibility(AssignmentVisibility visibility, ZonedDateTime appearAt, ErrorState state) {
        if (AssignmentVisibility.AFTER_DATE.equals(visibility) && Objects.isNull(appearAt)) {
            state.addError("validation.assignment.visibility.appearAt.required", "Appear At date is required if visibility status is 'After Date'.");
        }
    }

    private void validateDeadline(ZonedDateTime deadline, ErrorState state) {
        if (ZonedDateTime.now().isAfter(deadline)) {
            state.addError("validation.assignment.deadline.inThePast", "Deadline cannot be in the past.");
        }
    }

    private void validateAppearAtDateWithDeadline(ZonedDateTime appearAt, ZonedDateTime deadline, ErrorState state) {
        if (ObjectUtils.anyNull(appearAt, deadline)) {
            return;
        }
        if (deadline.isBefore(appearAt)) {
            state.addError("validation.assignment.deadline.beforeAppearAt", "Deadline cannot be before the 'Appear At' date.");
        }
    }
}
