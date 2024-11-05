package com.pnu.system.identityaccess.event.model;

import com.pnu.system.identityaccess.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateEvent {

    private User user;

}
