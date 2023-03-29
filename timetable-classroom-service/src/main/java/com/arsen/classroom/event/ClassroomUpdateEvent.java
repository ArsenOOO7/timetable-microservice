package com.arsen.classroom.event;

import com.arsen.common.event.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ClassroomUpdateEvent{

    private long id;
    private String name;
    private String address;

    private EntityStatus status;

}
