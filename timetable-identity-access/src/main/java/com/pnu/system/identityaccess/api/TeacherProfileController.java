package com.pnu.system.identityaccess.api;

import com.pnu.system.identityaccess.api.dto.TeacherProfileUpdateRequest;
import com.pnu.system.identityaccess.domain.TeacherProfile;
import com.pnu.system.identityaccess.service.TeacherProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher/profile")
public class TeacherProfileController {

    private final TeacherProfileService service;

    @PutMapping
    public TeacherProfile update(@Valid @RequestBody TeacherProfileUpdateRequest request) {
        return service.update(request);
    }

    @GetMapping("/{id}")
    public TeacherProfile getById(@PathVariable String id) {
        return service.getOne(id);
    }
}
