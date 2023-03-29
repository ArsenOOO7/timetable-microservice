package com.arsen.timetable.event;

import com.arsen.common.event.EntityStatus;
import com.arsen.timetable.dto.readonly.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class SubjectUpdateEvent extends SubjectDto {

    public SubjectUpdateEvent(long id, String subjectName, EntityStatus status) {
        super(id, subjectName);
        this.status = status;
    }

    private EntityStatus status;

}
