package com.pnu.system.laboratorycontrol.event;

import com.pnu.system.laboratorycontrol.domain.Assignment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AssignmentCreateEvent {

    private final Assignment assignment;

}
