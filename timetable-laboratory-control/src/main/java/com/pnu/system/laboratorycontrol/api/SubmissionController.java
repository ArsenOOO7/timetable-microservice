package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.laboratorycontrol.api.dto.SubmissionDto;
import com.pnu.system.laboratorycontrol.constant.SubmissionStatus;
import com.pnu.system.laboratorycontrol.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService service;

    @PutMapping("/{id}")
    public void updateStatus(@PathVariable String id, @RequestParam SubmissionStatus status) {
        service.updateStatus(id, status);
    }

    @GetMapping("/list")
    public List<SubmissionDto> getList(@RequestParam String subAssignmentId, @RequestParam String authorId) {
        return service.getList(subAssignmentId, authorId);
    }
}
