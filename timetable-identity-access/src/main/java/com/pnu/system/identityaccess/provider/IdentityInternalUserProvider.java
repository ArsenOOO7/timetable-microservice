package com.pnu.system.identityaccess.provider;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.security.constant.SecurityConstants;
import com.pnu.system.common.security.model.UserDetails;
import com.pnu.system.common.security.provider.InternalUserProvider;
import com.pnu.system.common.utils.JwtUtils;
import com.pnu.system.identityaccess.domain.User;
import com.pnu.system.identityaccess.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Component
public class IdentityInternalUserProvider implements InternalUserProvider {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Value("${timetable.security.starts:Bearer}")
    private String tokenStarts;

    private DecodedJWT token;

    @Override
    public String getInternalTokenForRequest() {
        if (token == null || token.getExpiresAtAsInstant().isBefore(Instant.now())) {
            retrieveToken();
        }
        return String.join(" ", tokenStarts, token.getToken());
    }

    private void retrieveToken() {
        User user = userService.getOne(SecurityConstants.INTERNAL_USER_ID);
        UserDetails userDetails = new UserDetails();
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setPermissions(List.of(PermissionName.INTERNAL_USE.name()));
        token = jwtUtils.verifyToken(tokenStarts + jwtUtils.generateToken(userDetails));
    }
}
