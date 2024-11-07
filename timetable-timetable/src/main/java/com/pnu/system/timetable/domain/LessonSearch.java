package com.pnu.system.timetable.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "lesson")
public class LessonSearch extends AbstractLesson {

    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private LessonType type;
    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private TimetableUserSnapshot teacher;
    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private TimetableSubjectSnapshot subject;
    @ManyToOne
    @JoinColumn(name = "lesson_location_id", insertable = false, updatable = false)
    private TimetableLessonLocationSnapshot location;
    @ManyToMany
    @JoinTable(name = "lesson_group",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<TimetableGroupSnapshot> groups;

}
