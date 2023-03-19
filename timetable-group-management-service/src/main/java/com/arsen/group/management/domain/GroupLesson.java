package com.arsen.group.management.domain;

import jakarta.persistence.*;
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

}
