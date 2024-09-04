package com.pnu.system.api;

import com.pnu.system.api.dto.UserCreateDto;
import com.pnu.system.api.dto.UserCredentialDto;
import com.pnu.system.api.dto.UserTokenResponse;
import com.pnu.system.domain.User;
import com.pnu.system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public UserTokenResponse createUser(@RequestBody @Validated UserCreateDto createDto) {
        return authService.signUp(createDto);
    }

    @PostMapping("/signIn")
    public UserTokenResponse signIn(@RequestBody @Validated UserCredentialDto credential) {
        return authService.signIn(credential);
    }

    @GetMapping("/whoami")
    public User whoami() {
        return authService.whoami();
    }
}