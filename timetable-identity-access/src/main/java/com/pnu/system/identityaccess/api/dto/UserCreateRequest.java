package com.pnu.system.identityaccess.api.dto;

import com.pnu.system.common.constant.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserCreateRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private UserType type;

    @NotEmpty
    private List<String> roleIds;
    private List<String> groupIds;

}