package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.file.storage.dto.AttachmentDto;
import com.pnu.system.laboratorycontrol.service.SubmissionAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/submission/attachment")
public class SubmissionAttachmentController {

    private final SubmissionAttachmentService service;

    //TODO 6/25/25: REFACTOR!!

    @PostMapping(params = {"subAssignmentId"})
    public AttachmentDto createNew(@RequestParam String subAssignmentId, @RequestParam MultipartFile file) {
        return service.createNew(subAssignmentId, file);
    }

    @PostMapping(params = {"submissionId"})
    public AttachmentDto createForExisting(@RequestParam String submissionId, @RequestParam MultipartFile file) {
        return service.createForExisting(submissionId, file);
    }

    @GetMapping("/list")
    public List<AttachmentDto> getList(@RequestParam String submissionId) {
        return service.getList(submissionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String id) {
        return service.download(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
