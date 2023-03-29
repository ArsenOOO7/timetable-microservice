package com.arsen.teacher.event;

import com.arsen.common.event.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class TeacherUpdateEvent {

    private long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String meetingLink;
    private EntityStatus status;

}
