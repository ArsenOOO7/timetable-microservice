package com.pnu.system.group.domain;

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
@Table(name = "group")
public class GroupSearch extends AbstractGroup {

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private GroupSearch parent;
    @ManyToMany
    @JoinTable(name = "collective_group",
            joinColumns = @JoinColumn(name = "collective_group_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<GroupSearch> relatedGroups;
    @ManyToMany
    @JoinTable(name = "group_category_link",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "group_category_id"))
    private List<GroupCategory> groupCategories;

}
