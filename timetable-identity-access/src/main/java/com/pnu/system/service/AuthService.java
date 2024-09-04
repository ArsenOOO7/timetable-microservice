package com.pnu.system.service;

import com.pnu.system.api.auth.dto.UserCreateDto;
import com.pnu.system.api.auth.dto.UserCredentialDto;
import com.pnu.system.api.auth.dto.UserTokenResponse;
import com.pnu.system.common.domain.UserJWTDetails;
import com.pnu.system.common.utils.JwtUtils;
import com.pnu.system.domain.User;
import com.pnu.system.exception.UserEmailAlreadyExistsException;
import com.pnu.system.exception.UserNotFoundException;
import com.pnu.system.mapper.UserMapper;
import com.pnu.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserTokenResponse signUp(UserCreateDto userCreateDto) {
        User entity = userMapper.toUser(userCreateDto);
        Optional<User> user = userService.getByEmail(entity.getEmail());

        if (user.isPresent()) {
            throw new UserEmailAlreadyExistsException("The user with this email already exists");
        }

        entity.setRoles(new ArrayList<>());
        User newUser = userService.create(entity);

        return UserTokenResponse.builder()
                .token(jwtUtils.generateToken(userMapper.toBaseUserDetails(newUser)))
                .build();
    }

    public UserTokenResponse signIn(UserCredentialDto credential) {
        User user = userService.getByEmail(credential.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));



        return UserTokenResponse.builder()
                .token(jwtUtils.generateToken(userMapper.toBaseUserDetails(user)))
                .build();
    }

    @Transactional(readOnly = true)
    public User whoami() {
        UserJWTDetails user = (UserJWTDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getOne(user.getId());
    }
}
