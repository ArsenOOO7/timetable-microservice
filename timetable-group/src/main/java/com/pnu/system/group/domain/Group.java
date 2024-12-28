package com.pnu.system.group.domain;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "group")
public class Group extends AbstractGroup {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "collective_group", joinColumns = @JoinColumn(name = "collective_group_id"))
    @Column(name = "group_id", insertable = false, updatable = false)
    private List<String> relatedGroupIds;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "group_category_link", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "group_category_id", insertable = false, updatable = false)
    private List<String> groupCategoryIds;

    //TODO 12/24/24: Think about it daily, at 7:10, 7:20, ... 23:59
    @ManyToMany
    @JoinTable(name = "collective_group",
            joinColumns = @JoinColumn(name = "collective_group_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> relatedGroups;
    @ManyToMany
    @JoinTable(name = "group_category_link",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "group_category_id"))
    private List<GroupCategory> groupCategories;

    public List<String> getRelatedGroupIds() {
        if (relatedGroups == null) {
            relatedGroups = new ArrayList<>();
        }
        return relatedGroupIds;
    }

    public List<String> getGroupCategoryIds() {
        if (groupCategoryIds == null) {
            groupCategoryIds = new ArrayList<>();
        }
        return groupCategoryIds;
    }
}
