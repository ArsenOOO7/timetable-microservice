package com.arsen.common.security;


import co.elastic.clients.util.TriFunction;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * JWTUtils for JWT Tokens
 *
 * @author Arsen Sydoryk
 */
public class JWTUtils {

    public static String createToken(String email, String role, String secret, long expires){

        return JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withExpiresAt(Instant.now().plusSeconds(60 * expires))
                .withIssuedAt(Instant.now())
                .withIssuer("identity-access-service")
                .sign(Algorithm.HMAC256(secret.getBytes()));

    }

    public static void extractData(Map<String, String> data, BiConsumer<String, String> createTokenLogic){
        extractData(data, createTokenLogic, (token, email, role) -> false);
    }

    public static void extractData(Map<String, String> data, BiConsumer<String, String> createTokenLogic, TriFunction<String, String, String, Boolean> validateFunction){
        try {

            String token = data.get("token");
            Algorithm algorithm = Algorithm.HMAC256(data.get("secret").getBytes());

            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if(!isExpired(decodedJWT.getExpiresAtAsInstant())) {

                String email = decodedJWT.getSubject();
                String role = decodedJWT.getClaim("role").asString();

                String emailHeader = data.get("emailHeader");
                String roleHeader = data.get("roleHeader");

                boolean valid;
                if(emailHeader == null && roleHeader == null) {
                    valid = validateFunction.apply(token, email, role);
                }else{
                    valid = email.equals(emailHeader) && role.equals(roleHeader);
                }

                if(valid) {
                    createTokenLogic.accept(email, role);
                }
            }

        }catch (Exception exception){
            throw exception;
        }
    }

    public static boolean isExpired(Instant expired){
        return expired.isBefore(Instant.now());
    }


}
