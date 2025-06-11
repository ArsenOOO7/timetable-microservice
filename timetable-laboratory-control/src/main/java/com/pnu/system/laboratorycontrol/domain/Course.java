package com.pnu.system.laboratorycontrol.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course extends AbstractCourse {

    @Column(name = "subject_id")
    private String subjectId;
    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "course_authors", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "author_id")
    private Set<String> authorIds;
    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "course_groups", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "group_id")
    private Set<String> groupIds;

    public Set<String> getAuthorIds() {
        if (Objects.isNull(authorIds)) {
            authorIds = new HashSet<>();
        }
        return authorIds;
    }

    public Set<String> getGroupIds() {
        if (Objects.isNull(groupIds)) {
            groupIds = new HashSet<>();
        }
        return groupIds;
    }
}
