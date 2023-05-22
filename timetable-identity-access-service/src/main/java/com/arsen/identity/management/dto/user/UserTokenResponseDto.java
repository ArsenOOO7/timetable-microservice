package com.arsen.identity.management.dto.user;

import com.arsen.identity.management.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenResponseDto {

    private String email;
    private String role;

    public UserTokenResponseDto(String email, Role role) {
        this.email = email;
        this.role = role.name();
    }
}


