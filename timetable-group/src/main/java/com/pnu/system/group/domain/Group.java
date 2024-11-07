package com.pnu.system.group.domain;


import com.pnu.system.common.domain.AuditableEntity;
import com.pnu.system.group.constant.GroupType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Group extends AuditableEntity {

    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "specialty_id")
    private String specialtyId;
    @Column(name = "academic_year")
    private int academicYear;
    @Column(name = "number")
    private int number;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GroupType type;
    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "collective_group", joinColumns = @JoinColumn(name = "collective_group_id"))
    @Column(name = "group_id", insertable = false, updatable = false)
    private List<String> relatedGroupIds;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "group_category_link", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "group_category_id", insertable = false, updatable = false)
    private List<String> groupCategoryIds;

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
