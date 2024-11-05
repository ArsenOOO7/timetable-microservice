package com.pnu.system.group.event.model;

import com.pnu.system.group.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupCreateEvent {

    private Group group;

}
