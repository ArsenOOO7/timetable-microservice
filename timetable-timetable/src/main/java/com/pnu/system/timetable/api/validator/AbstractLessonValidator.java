package com.pnu.system.timetable.api.validator;

import com.pnu.system.timetable.domain.TimetableUserSnapshot;
import com.pnu.system.timetable.service.TimetableUserSnapshotService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class AbstractLessonValidator implements Validator {

    @Autowired
    private TimetableUserSnapshotService userSnapshotService;

    protected void validateOnline(String lessonLocationId, boolean online, Errors errors) {
        if (online == StringUtils.isNotBlank(lessonLocationId)) {
            errors.reject("", "Lesson Location should be specified if it's not online.");
        }
    }

    protected void validateTeacherPersonalLink(String teacherId, Errors errors) {
        TimetableUserSnapshot teacher = userSnapshotService.getById(teacherId);
        if (StringUtils.isBlank(teacher.getPersonalLink())) {
            errors.reject("", "Teacher's personal link is required if it's online.");
        }
    }
}
