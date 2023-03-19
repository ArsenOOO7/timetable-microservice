package com.arsen.timetable.domain.readonly;

import com.arsen.timetable.domain.Lesson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class TeacherRead {

    @Id
    private long id;

    @Column(nullable = false)
    private String firstName;


    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false)
    private String fatherName;

    @Column
    private String meetingLink;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Set<Lesson> lessons;

    public TeacherRead(long id, String firstName, String lastName, String fatherName, String meetingLink) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.meetingLink = meetingLink;
    }
}
