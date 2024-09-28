package com.pnu.system.common.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalCredentialRequest {

    private String email;
    private String password;

}
