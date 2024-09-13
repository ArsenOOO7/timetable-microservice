package com.pnu.system.identityaccess.api;

import com.pnu.system.identityaccess.api.dto.UserCredentialDto;
import com.pnu.system.identityaccess.api.dto.UserTokenResponse;
import com.pnu.system.identityaccess.api.dto.UserWhoamiResponseDto;
import com.pnu.system.identityaccess.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/login")
    public UserTokenResponse login(@Valid @RequestBody UserCredentialDto credential) {
        return authService.login(credential);
    }

    @GetMapping("/whoami")
    public UserWhoamiResponseDto whoami() {
        return authService.whoami();
    }
}