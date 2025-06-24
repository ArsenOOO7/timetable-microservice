package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentDto;
import com.pnu.system.laboratorycontrol.api.dto.SubAssignmentUpdateRequest;
import com.pnu.system.laboratorycontrol.service.SubAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subAssignment")
public class SubAssignmentController {

    private final SubAssignmentService service;

    @PostMapping
    public SubAssignmentDto create(@Valid @RequestBody SubAssignmentCreateRequest request) {
        return service.create(request);
    }

    @PutMapping
    public SubAssignmentDto update(@Valid @RequestBody SubAssignmentUpdateRequest request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/list")
    public List<SubAssignmentDto> getList(@RequestParam String assignmentId) {
        return service.getList(assignmentId);
    }
}
