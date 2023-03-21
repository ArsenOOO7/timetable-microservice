package com.arsen.group.management.event;

import com.arsen.group.management.dto.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupEventUpdate extends GroupDto {

    public GroupEventUpdate(long id, String fullName, boolean collective, Set<Long> groupIds, GroupStatus groupStatus) {
        super(id, fullName, collective, groupIds);
        this.status = groupStatus;
    }

    public enum GroupStatus{
        CREATED,
        UPDATED,
        DELETED;
    }

    private GroupStatus status;



}
