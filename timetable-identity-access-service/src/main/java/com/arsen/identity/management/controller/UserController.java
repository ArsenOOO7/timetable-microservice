package com.arsen.identity.management.controller;

import com.arsen.identity.management.dto.user.UserCreateDto;
import com.arsen.identity.management.dto.user.UserPasswordChangeDto;
import com.arsen.identity.management.dto.user.UserResponseDto;
import com.arsen.identity.management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'UNIT_MANAGER')")
    public ResponseEntity<UserResponseDto> read(@PathVariable long id){
        return ResponseEntity.ok(userService.readById(id, true));
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'UNIT_MANAGER')")
    public void updatePassword(@PathVariable long id, @Valid @RequestBody UserPasswordChangeDto passwordChangeDto){
        userService.updatePassword(id, passwordChangeDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void delete(@PathVariable long id){
        userService.delete(id);
    }

}
