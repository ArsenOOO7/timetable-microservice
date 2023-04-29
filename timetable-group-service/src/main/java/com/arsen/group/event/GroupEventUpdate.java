package com.arsen.group.event;

import com.arsen.common.event.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GroupEventUpdate {

    private long id;
    private String fullName;

    private boolean collective;

    private EntityStatus status;

    private Set<Long> groupIds;

}


