package com.arsen.timetable.event;

import com.arsen.common.event.EntityStatus;
import com.arsen.timetable.dto.readonly.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class TeacherUpdateEvent extends TeacherDto {

    public TeacherUpdateEvent(long id, String firstName, String lastName, String fatherName, String meetingLink, EntityStatus status) {
        super(id, firstName, lastName, fatherName, meetingLink);
        this.status = status;
    }

    private EntityStatus status;

}
