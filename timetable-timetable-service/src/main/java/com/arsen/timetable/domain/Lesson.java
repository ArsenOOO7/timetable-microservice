package com.arsen.timetable.domain;

import com.arsen.timetable.domain.readonly.ClassroomRead;
import com.arsen.timetable.domain.readonly.SubjectRead;
import com.arsen.timetable.domain.readonly.TeacherRead;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "timetable")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherRead teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectRead subject;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassroomRead classroom;

    @Column(nullable = false)
    private short lessonNumber;

    @Column(nullable = false)
    private LocalDate lessonDate;

    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @Column(nullable = false)
    private boolean online = false;


}
