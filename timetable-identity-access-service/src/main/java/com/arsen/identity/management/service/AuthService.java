package com.arsen.identity.management.service;

import com.arsen.common.security.JWTUtils;
import com.arsen.identity.management.domain.JwtToken;
import com.arsen.identity.management.domain.User;
import com.arsen.identity.management.dto.CredentialsDto;
import com.arsen.identity.management.dto.JwtTokenResponseDto;
import com.arsen.identity.management.dto.user.UserTokenResponseDto;
import com.arsen.identity.management.exception.InvalidCredentialsException;
import com.arsen.identity.management.exception.InvalidTokenException;
import com.arsen.identity.management.repository.JwtTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenRepository jwtTokenRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expires}")
    private long expires;


    public JwtTokenResponseDto saveToken(User user){

        JwtToken jwtToken = jwtTokenRepository.findById(user.getId()).orElseGet(() -> new JwtToken(user.getId()));
        jwtToken.setToken(JWTUtils.createToken(user.getEmail(), user.getRole().name(), secret, expires));

        jwtTokenRepository.save(jwtToken);

        return new JwtTokenResponseDto(jwtToken.getToken());
    }

    public JwtTokenResponseDto login(CredentialsDto credentialsDto){
        User user = userService.readByEmail(credentialsDto.getEmail());
        if(user == null){
            throw new InvalidCredentialsException("Invalid email or password!");
        }

        String password = user.getPassword();
        if(!passwordEncoder.matches(credentialsDto.getPassword(), password)){
            throw new InvalidCredentialsException("Invalid email or password!");
        }
        return saveToken(user);
    }


    public boolean validToken(String token, String email, String role){
        UserTokenResponseDto user = getUserByToken(token);
        return user.getEmail().equals(email) && user.getRole().equals(role);
    }

    private UserTokenResponseDto getUserByToken(String token){

        JwtToken jwtToken = jwtTokenRepository.readByToken(token);
        if(jwtToken == null){
            throw new InvalidTokenException("Invalid token!");
        }

        return userService.readByTokenId(jwtToken.getId());
    }
}
