package com.pnu.system.timetable.api.validator;

import com.pnu.system.timetable.api.dto.LessonCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class LessonCreateValidator extends AbstractLessonValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LessonCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LessonCreateRequest request = (LessonCreateRequest) target;

        validateOnline(request.getLessonLocationId(), request.getOnline(), errors);
        if (request.getOnline()) {
            validateTeacherPersonalLink(request.getTeacherId(), errors);
        }
    }
}
