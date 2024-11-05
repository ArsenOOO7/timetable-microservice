package com.pnu.system.timetable.api.validator;

import com.pnu.system.timetable.api.dto.LessonUpdateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class LessonUpdateValidator extends AbstractLessonValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LessonUpdateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LessonUpdateRequest request = (LessonUpdateRequest) target;

        validateOnline(request.getLessonLocationId(), request.getOnline(), errors);
        if (request.getOnline()) {
            validateTeacherPersonalLink(request.getTeacherId(), errors);
        }
    }
}
