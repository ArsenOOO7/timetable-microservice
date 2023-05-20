package com.arsen.group.management.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class GroupRead {

    @Id
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    boolean collective = false;

    @ManyToMany
    @JoinTable(name = "collective_groups", joinColumns = @JoinColumn(name = "collective_group_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupRead> groups = new HashSet<>();

    @ManyToMany(mappedBy = "groups")
    private Set<GroupRead> collectiveGroups = new HashSet<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<GroupLesson> lessons;

    public void resolveGroups(Set<GroupRead> groupSet) {
        groups.removeIf(group -> !groupSet.contains(group));
        groups.addAll(groupSet);
    }


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
