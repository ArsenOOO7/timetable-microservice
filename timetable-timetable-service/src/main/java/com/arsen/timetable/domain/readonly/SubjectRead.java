package com.arsen.timetable.domain.readonly;

import com.arsen.timetable.domain.Lesson;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class SubjectRead {

    @Id
    private long id;

    @Column(nullable = false)
    private String subjectName;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private Set<Lesson> lessons;


    public SubjectRead(long id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }
}
