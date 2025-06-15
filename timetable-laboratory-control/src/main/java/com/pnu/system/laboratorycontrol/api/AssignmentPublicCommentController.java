package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentCommentDto;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentCommentSearchRequest;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPublicCommentCreateRequest;
import com.pnu.system.laboratorycontrol.service.AssignmentPublicCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/assignment/public/comment")
public class AssignmentPublicCommentController {

    private final AssignmentPublicCommentService service;

    @PostMapping
    public void create(@Valid @RequestBody AssignmentPublicCommentCreateRequest request) {
        service.create(request);
    }

    @PostMapping("/list")
    public List<AssignmentCommentDto> getList(@Valid @RequestBody AssignmentCommentSearchRequest request) {
        return service.getList(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
