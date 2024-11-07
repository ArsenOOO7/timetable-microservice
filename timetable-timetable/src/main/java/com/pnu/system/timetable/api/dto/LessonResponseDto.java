package com.pnu.system.timetable.api.dto;

import com.pnu.system.timetable.domain.LessonType;
import com.pnu.system.timetable.domain.TimetableGroupSnapshot;
import com.pnu.system.timetable.domain.TimetableLessonLocationSnapshot;
import com.pnu.system.timetable.domain.TimetableSubjectSnapshot;
import com.pnu.system.timetable.domain.TimetableUserSnapshot;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LessonResponseDto {

    private String id;

    private LocalDate date;
    private short number;

    private LessonType type;
    private TimetableLessonLocationSnapshot location;
    private TimetableSubjectSnapshot subject;
    private TimetableUserSnapshot teacher;

    private boolean online;

    private List<TimetableGroupSnapshot> groups;

    private int version;

}
