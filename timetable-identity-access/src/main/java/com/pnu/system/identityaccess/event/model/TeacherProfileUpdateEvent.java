package com.pnu.system.identityaccess.event.model;

import com.pnu.system.identityaccess.domain.TeacherProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherProfileUpdateEvent {

    private TeacherProfile profile;

}
