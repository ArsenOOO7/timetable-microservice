package com.pnu.system.identityaccess.api.dto;

import com.pnu.system.common.constant.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType type;
    private List<String> groupIds;
    private List<UserRoleDto> roles;
    private Integer version;

}
