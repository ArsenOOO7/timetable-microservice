package com.pnu.system.common.security.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetails {

    private String id;
    private String email;
    private List<String> permissions;

}
