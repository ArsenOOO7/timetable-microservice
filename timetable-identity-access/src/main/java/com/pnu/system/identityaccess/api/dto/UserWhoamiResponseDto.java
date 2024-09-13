package com.pnu.system.identityaccess.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWhoamiResponseDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> permissions;

}
