package com.pnu.system.identityaccess.api;

import com.pnu.system.identityaccess.domain.AcademicStatus;
import com.pnu.system.identityaccess.service.AcademicStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/academicStatus")
public class AcademicStatusController {

    private final AcademicStatusService service;

    @PostMapping
    public AcademicStatus create(@Valid @RequestBody AcademicStatus academicStatus) {
        return service.create(academicStatus);
    }

    @PutMapping
    public AcademicStatus update(@Valid @RequestBody AcademicStatus academicStatus) {
        return service.update(academicStatus);
    }

    @GetMapping("/{id}")
    public AcademicStatus getById(@PathVariable String id) {
        return service.getOne(id);
    }

    @GetMapping("/list")
    public List<AcademicStatus> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
