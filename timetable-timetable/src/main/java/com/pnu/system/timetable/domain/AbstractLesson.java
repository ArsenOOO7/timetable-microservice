package com.pnu.system.timetable.domain;

import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
public class AbstractLesson extends AuditableEntity {

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "number")
    private short number;

    @Column(name = "type_id")
    private String typeId;
    @Column(name = "teacher_id")
    private String teacherId;
    @Column(name = "subject_id")
    private String subjectId;
    @Column(name = "lesson_location_id")
    private String lessonLocationId;

    @Column(name = "online")
    private boolean online;

    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "lesson_group", joinColumns = @JoinColumn(name = "lesson_id"))
    @Column(name = "group_id")
    private List<String> groupIds;

}
