package com.pnu.system.api.user;

import com.pnu.system.domain.User;
import com.pnu.system.mapper.UserMapper;
import com.pnu.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasPermission(null, 'ROLE_EDIT')")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
