package com.pnu.system.laboratorycontrol.api.validation;

import com.pnu.system.common.exception.ValidationException;
import com.pnu.system.laboratorycontrol.constant.CourseStatus;
import com.pnu.system.laboratorycontrol.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseValidation {

    public void validateBeforeAuthorRemoval(Course course) {
        if (course.getAuthorIds().size() == 1) {
            throw new ValidationException("validation.course.authorRemoval.forbidden", "The Course must contain at least 1 author.");
        }
    }

    public void validateBeforeArchive(Course course) {
        if (CourseStatus.ARCHIVED.equals(course.getStatus())) {
            throw new ValidationException("validation.course.archive.already", "Course is already Archived");
        }
    }

    public void validateBeforeMakingActive(Course course) {
        if (CourseStatus.ARCHIVED.equals(course.getStatus())) {
            throw new ValidationException("validation.course.active.already", "Course is already Active");
        }
    }
}
