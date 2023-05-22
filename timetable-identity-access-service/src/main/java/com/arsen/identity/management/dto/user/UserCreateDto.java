package com.arsen.identity.management.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UserCreateDto {

    private String email;
    private String password;

}
