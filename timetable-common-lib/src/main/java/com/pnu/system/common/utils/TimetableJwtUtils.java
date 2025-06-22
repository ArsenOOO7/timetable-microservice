package com.pnu.system.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pnu.system.common.exception.InvalidTokenException;
import com.pnu.system.common.security.model.UserDetails;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.pnu.system.common.security.constant.TokenClaims.CLAIM_KEY_EMAIL;
import static com.pnu.system.common.security.constant.TokenClaims.CLAIM_KEY_PERMISSIONS;

@Component
public class TimetableJwtUtils {

    @Value("${timetable.security.secret:TimetableTopApplication}")
    private String jwtSecret;
    @Value("${timetable.security.lifetime:172800}")
    private int lifetime;
    @Value("${timetable.security.starts:Bearer}")
    private String tokenStarts;

    private JWTVerifier verifier;

    @PostConstruct
    private void init() {
        verifier = JWT.require(getSignKey()).build();
    }

    public String generateToken(UserDetails user) {
        return JWT.create()
                .withSubject(user.getId())
                .withClaim(CLAIM_KEY_PERMISSIONS, user.getPermissions())
                .withClaim(CLAIM_KEY_EMAIL, user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(lifetime))
                .sign(getSignKey());
    }

    public DecodedJWT verifyToken(String header) {
        if (StringUtils.isBlank(header) || !StringUtils.startsWith(header, tokenStarts)) {
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
