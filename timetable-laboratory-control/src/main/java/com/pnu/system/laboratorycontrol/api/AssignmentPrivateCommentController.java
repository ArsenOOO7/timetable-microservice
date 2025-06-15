package com.pnu.system.laboratorycontrol.api;

import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentDto;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentSearchRequest;
import com.pnu.system.laboratorycontrol.service.AssignmentPrivateCommentService;
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
@RequestMapping("/assignment/private/comment")
public class AssignmentPrivateCommentController {

    private final AssignmentPrivateCommentService service;

    @PostMapping
    public void create(@Valid @RequestBody AssignmentPrivateCommentCreateRequest request) {
        service.create(request);
    }

    @PostMapping("/list")
    public List<AssignmentPrivateCommentDto> getList(@Valid @RequestBody AssignmentPrivateCommentSearchRequest request) {
        return service.getList(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
