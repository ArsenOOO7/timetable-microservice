package com.arsen.group.event;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GroupEventUpdate implements Serializable {

    private long id;
    private String fullName;

    private boolean collective;

    private GroupStatus status;

    private Set<Long> groupIds;

    public enum GroupStatus implements Serializable{
        CREATED,
        UPDATED,
        DELETED;
    }

}


