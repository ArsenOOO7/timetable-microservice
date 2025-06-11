package com.pnu.system.laboratorycontrol.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course")
public class CourseSearch extends AbstractCourse {

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private LaboratoryControlSubjectSnapshot subject;
    @ManyToMany
    @JoinTable(name = "course_authors",
            joinColumns = @JoinColumn(name = "course_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "author_id", insertable = false, updatable = false))
    private List<LaboratoryControlUserSnapshot> authors;
    @ManyToMany
    @JoinTable(name = "course_groups",
            joinColumns = @JoinColumn(name = "course_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "group_id", insertable = false, updatable = false))
    private List<LaboratoryControlGroupSnapshot> groups;

}
