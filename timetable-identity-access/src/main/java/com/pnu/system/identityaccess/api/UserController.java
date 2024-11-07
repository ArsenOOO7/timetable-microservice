package com.pnu.system.identityaccess.api;

import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import com.pnu.system.identityaccess.api.dto.UserPreviewDto;
import com.pnu.system.identityaccess.api.dto.UserResponseDto;
import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.api.validator.UserCreateValidator;
import com.pnu.system.identityaccess.api.validator.UserUpdateValidator;
import com.pnu.system.identityaccess.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserCreateValidator userCreateValidator;
    private final UserUpdateValidator userUpdateValidator;

    @InitBinder("userCreateRequest")
    public void initUserCreateRequestBinder(WebDataBinder binder) {
        binder.addValidators(userCreateValidator);
    }

    @InitBinder("userUpdateRequest")
    public void initUserUpdateRequestBinder(WebDataBinder binder) {
        binder.addValidators(userUpdateValidator);
    }

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @PutMapping
    public UserResponseDto update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable String id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @PostMapping("/list")
    public List<UserPreviewDto> getUsers(@Valid @RequestBody BaseSearchRequest request) {
        return userService.getPreviewUsers(request);
    }

    @GetMapping("/{id}/internal/group/list")
    public List<String> getUserGroupIds(@PathVariable String id) {
        return userService.getUserGroupIds(id);
    }
}
