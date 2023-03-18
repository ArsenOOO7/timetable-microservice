package com.arsen.group.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String cypher;

    @Column(nullable = false)
    private short academicYear;

    @Column(nullable = false)
    private short number;

    @Column(nullable = false)
    private boolean master = false;

    @Column(nullable = false)
    private boolean college = false;

    @Column(nullable = false)
    private boolean collective = false;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "collective_groups",
        joinColumns = @JoinColumn(name = "collective_group_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    @ManyToMany(mappedBy = "groups")
    /*@JoinTable(name = "collective_groups",
            joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "collective_group_id"))*/
    private Set<Group> collectiveGroups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && academicYear == group.academicYear && number == group.number && master == group.master && college == group.college && collective == group.collective && Objects.equals(cypher, group.cypher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cypher, academicYear, number, master, college, collective);
    }
}
