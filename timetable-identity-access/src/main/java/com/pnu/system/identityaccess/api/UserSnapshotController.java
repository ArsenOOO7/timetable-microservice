package com.pnu.system.identityaccess.api;

import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.identityaccess.service.UserSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/internal/snapshot")
@RequiredArgsConstructor
public class UserSnapshotController {

    private final UserSnapshotService service;

    @GetMapping("/{id}")
    public UserSnapshotDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping("/list")
    public List<UserSnapshotDto> getByIds(@RequestBody List<String> ids) {
        return service.getByIds(ids);
    }
}
