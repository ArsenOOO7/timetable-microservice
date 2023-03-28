package com.arsen.subject.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SubjectUpdateEvent {

    private long id;

    private String subjectName;

    private EntityStatus status;

}
