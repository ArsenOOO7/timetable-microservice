package com.arsen.timetable.event;

import com.arsen.timetable.dto.readonly.ClassroomDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ClassroomUpdateEvent extends ClassroomDto {

    public ClassroomUpdateEvent(long id, String name, String address, EntityStatus status) {
        super(id, name, address);
        this.status = status;
    }

    private EntityStatus status;

}
