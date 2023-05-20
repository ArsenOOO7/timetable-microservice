package com.arsen.group.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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


    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "collective_groups",
        joinColumns = @JoinColumn(name = "collective_group_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    @ManyToMany(mappedBy = "groups")
    private Set<Group> collectiveGroups = new HashSet<>();

    public void addGroup(Group group){
        if(groups.add(group)) {
            group.getCollectiveGroups().add(this);
        }
    }

    public void removeGroup(Group group){
        if(groups.remove(group)){
            group.getCollectiveGroups().remove(this);
        }
    }

    public void addGroups(Set<Group> groupSet){
        for (Group group : groupSet) {
            addGroup(group);
        }
    }

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

    @Override
    public String toString() {
        return cypher +
                (master ? 'м' : "") +
                (college ? 'к' : "") +
                '-' +
                academicYear +
                (collective ? '.' : "") +
                number;
    }
}
