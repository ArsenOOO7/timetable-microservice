package com.pnu.system.identityaccess.api;

import com.pnu.system.identityaccess.api.dto.UserPreviewDto;
import com.pnu.system.identityaccess.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public List<UserPreviewDto> getUsers() {
        return userService.getPreviewUsers();
    }
}
