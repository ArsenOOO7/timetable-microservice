package com.pnu.system.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pnu.system.common.domain.UserJWTDetails;
import com.pnu.system.common.exception.InvalidTokenException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private static final String CLAIM_KEY_PERMISSIONS = "PERMISSIONS";

    @Value("${timetable.security.secret:TimetableTopApplication}")
    private String jwtSecret;
    @Value("${timetable.security.lifetime:172800}")
    private int lifetime;
    @Value("${timetable.security.starts:Bearer}")
    private String tokenStarts;

    private JWTVerifier verifier;

    @PostConstruct
    private void init(){
        verifier = JWT.require(getSignKey()).build();
    }

    public String generateToken(UserJWTDetails user) {
        return JWT.create()
                .withSubject(user.getId())
                .withClaim(CLAIM_KEY_PERMISSIONS, user.getPermissions())
                .withExpiresAt(Instant.now().plusSeconds(lifetime))
                .sign(getSignKey());
    }

    public UserJWTDetails getUserDetails(DecodedJWT decodedJWT) {
        List<String> permissions = decodedJWT.getClaim(CLAIM_KEY_PERMISSIONS).asList(String.class);

        UserJWTDetails userJWTDetails = new UserJWTDetails();
        userJWTDetails.setId(decodedJWT.getSubject());
        userJWTDetails.setPermissions(Optional.ofNullable(permissions).orElse(new ArrayList<>()));

        return userJWTDetails;
    }

    public void authenticate(String header) {
        DecodedJWT decodedJWT = verifyToken(header);

        if (Objects.isNull(decodedJWT)) {
            return;
        }

        UserJWTDetails userDetails = getUserDetails(decodedJWT);

        List<GrantedAuthority> authorities = userDetails.getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toUnmodifiableList());

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, authorities));
    }

    private DecodedJWT verifyToken(String header) {
        if (StringUtils.isBlank(header)
                || !StringUtils.startsWith(header, tokenStarts)) {
            return null;
        }

        String token = StringUtils.removeStart(header, tokenStarts).trim();
        DecodedJWT decodedJWT = verifier.verify(token);

        if (decodedJWT.getExpiresAtAsInstant().isBefore(Instant.now())) {
            throw new InvalidTokenException("Expired or invalid token");
        }

        return decodedJWT;
    }

    private Algorithm getSignKey() {
        return Algorithm.HMAC256(jwtSecret);
    }
}
