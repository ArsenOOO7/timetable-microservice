package com.arsen.group.event;

import com.arsen.common.event.EntityStatus;
import lombok.*;

import java.io.Serializable;
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


