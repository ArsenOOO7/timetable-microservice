package com.pnu.system.api.auth.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}