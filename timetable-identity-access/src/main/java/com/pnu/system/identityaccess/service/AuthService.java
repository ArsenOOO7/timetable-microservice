package com.pnu.system.identityaccess.service;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.security.model.UserDetails;
import com.pnu.system.common.utils.JwtUtils;
import com.pnu.system.common.utils.UserUtils;
import com.pnu.system.identityaccess.api.dto.UserCredentialDto;
import com.pnu.system.identityaccess.api.dto.UserTokenResponse;
import com.pnu.system.identityaccess.api.dto.UserWhoamiResponseDto;
import com.pnu.system.identityaccess.domain.Permission;
import com.pnu.system.identityaccess.domain.User;
import com.pnu.system.identityaccess.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthMapper authMapper;

    public UserTokenResponse login(UserCredentialDto credential) {
        User user = userService.getByEmail(credential.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User is not found by email %s.".formatted(credential.getEmail())));

        UserDetails userDetails = new UserDetails();
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setPermissions(authMapper.asStringPermissionNames(getUserPermissions(user)));

        return authMapper.asUserTokenResponse(jwtUtils.generateToken(userDetails));
    }

    public UserWhoamiResponseDto whoami() {
        User user = userService.getOne(UserUtils.getId());
        return authMapper.asUserWhoamiResponse(user, getUserPermissions(user));
    }

    private List<PermissionName> getUserPermissions(User user) {
        return user.getRoles().stream().flatMap(role -> role.getPermissions().stream())
                .map(Permission::getName)
                .distinct()
                .toList();
    }
}
