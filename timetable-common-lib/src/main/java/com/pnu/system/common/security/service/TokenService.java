package com.pnu.system.common.security.service;

import com.pnu.system.common.security.model.UserDetails;
import com.pnu.system.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.pnu.system.common.security.constant.TokenClaims.CLAIM_KEY_EMAIL;
import static com.pnu.system.common.security.constant.TokenClaims.CLAIM_KEY_PERMISSIONS;


@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtUtils jwtUtils;

    public void authenticate(String header) {
        Optional.ofNullable(header)
                .map(jwtUtils::verifyToken)
                .ifPresent(decodedJwt -> {
                    String userId = decodedJwt.getSubject();
                    List<String> permissions = decodedJwt.getClaim(CLAIM_KEY_PERMISSIONS).asList(String.class);

                    UserDetails userDetails = new UserDetails();
                    userDetails.setId(userId);
                    userDetails.setEmail(decodedJwt.getClaim(CLAIM_KEY_EMAIL).asString());

                    List<SimpleGrantedAuthority> authorities = permissions.stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList();
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, authorities));
                });
    }
}
