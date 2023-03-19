package com.arsen.group.management.domain;

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
public class GroupRead {

    @Id
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    boolean collective = false;

    @ManyToMany
    @JoinTable(name = "collective_groups", joinColumns = @JoinColumn(name = "collective_group_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupRead> groups;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<GroupRead> collectiveGroups;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<GroupLesson> lessons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupRead groupRead = (GroupRead) o;
        return id == groupRead.id && collective == groupRead.collective && Objects.equals(fullName, groupRead.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, collective);
    }
}
