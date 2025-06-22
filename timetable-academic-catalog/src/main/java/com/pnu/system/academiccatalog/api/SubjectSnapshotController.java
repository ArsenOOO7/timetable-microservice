package com.pnu.system.academiccatalog.api;

import com.pnu.system.academiccatalog.service.SubjectSnapshotService;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.common.utils.TimetableDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject/internal/snapshot")
@RequiredArgsConstructor
public class SubjectSnapshotController {

    private final SubjectSnapshotService service;

    @GetMapping("/modifiedAfter")
    public List<SubjectSnapshotDto> getModifiedAfterDate(@RequestParam String lastModifiedAt) {
        return service.getModifiedAfterDate(TimetableDateUtils.asZonedDateTime(lastModifiedAt));
    }

    @GetMapping("/{id}")
    public SubjectSnapshotDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping("/list")
    public List<SubjectSnapshotDto> getByIds(@RequestBody List<String> ids) {
        return service.getByIds(ids);
    }

}
