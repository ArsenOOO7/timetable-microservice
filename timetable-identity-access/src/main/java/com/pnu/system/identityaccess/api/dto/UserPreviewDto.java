package com.pnu.system.identityaccess.api.dto;

import com.pnu.system.common.constant.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPreviewDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType type;

}
