package com.arsen.timetable.domain.readonly;

import com.arsen.timetable.domain.Lesson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "classrooms")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ClassroomRead {

    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom")
    private Set<Lesson> lessons;

    public ClassroomRead(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
