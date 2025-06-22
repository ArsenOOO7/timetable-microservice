package com.pnu.system.common.dto;

import com.pnu.system.common.constant.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseDto implements UsernameProvider {

    private String firstName;
    private String lastName;
    private String email;
    private UserType type;

}
