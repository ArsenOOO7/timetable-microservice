package com.arsen.identity.management.controller;

import com.arsen.identity.management.dto.CredentialsDto;
import com.arsen.identity.management.dto.JwtTokenResponseDto;
import com.arsen.identity.management.dto.user.UserTokenResponseDto;
import com.arsen.identity.management.exception.InvalidTokenException;
import com.arsen.identity.management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<UserTokenResponseDto> readByToken(Authentication principal){

        if(principal == null){
            throw new InvalidTokenException("Invalid token!");
        }

        String role = "";
        for (GrantedAuthority authority : principal.getAuthorities()) {
            role = authority.getAuthority();
        }

        return ResponseEntity.ok(new UserTokenResponseDto(principal.getName(), role));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponseDto> login(@RequestBody CredentialsDto credentialsDto){
        return ResponseEntity.ok(authService.login(credentialsDto));
    }

}
