package com.pnu.system.common.security.provider;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pnu.system.common.security.model.InternalCredentialRequest;
import com.pnu.system.common.security.model.InternalCredentialResponse;
import com.pnu.system.common.utils.TimetableJwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

public class RestInternalUserProvider implements InternalUserProvider {

    private final WebClient webClient;
    private final TimetableJwtUtils jwtUtils;

    @Value("${baseUrl.identity_access}/auth/login")
    private String authUrl;

    @Value("${timetable.security.starts:Bearer}")
    private String tokenStarts;
    @Value("${internal.user.login}")
    private String login;
    @Value("${internal.user.password}")
    private String password;

    private DecodedJWT internalToken;

    public RestInternalUserProvider(TimetableJwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
        webClient = WebClient.create();
    }

    public String getInternalTokenForRequest() {
        return String.join(" ", tokenStarts, getInternalUserToken());
    }

    public String getInternalUserToken() {
        if (internalToken == null || internalToken.getExpiresAtAsInstant().isBefore(Instant.now())) {
            requestInternalUserToken();
        }
        return internalToken.getToken();
    }

    private void requestInternalUserToken() {
        internalToken = webClient.post()
                .uri(authUrl)
                .bodyValue(buildInternalCredentialRequest())
                .retrieve()
                .bodyToMono(InternalCredentialResponse.class)
                .map(token -> jwtUtils.verifyToken(tokenStarts + token.getToken()))
                .block();
    }

    private InternalCredentialRequest buildInternalCredentialRequest() {
        InternalCredentialRequest request = new InternalCredentialRequest();
        request.setEmail(login);
        request.setPassword(password);
        return request;
    }
}
