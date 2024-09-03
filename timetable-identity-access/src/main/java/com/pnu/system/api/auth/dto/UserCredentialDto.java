package com.pnu.system.api.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialDto {
    private String email;
    private String password;
}
