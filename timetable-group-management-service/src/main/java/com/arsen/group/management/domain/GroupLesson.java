package com.arsen.group.management.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "groups_lessons")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class GroupLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long lessonId;

    @Column(nullable = false)
    private LocalDate lessonDate;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupRead group;

    public GroupLesson(long lessonId, LocalDate lessonDate, GroupRead group) {
        this.lessonId = lessonId;
        this.lessonDate = lessonDate;
        this.group = group;
    }
}
