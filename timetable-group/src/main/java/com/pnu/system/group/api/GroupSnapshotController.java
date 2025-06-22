package com.pnu.system.group.api;

import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.common.utils.TimetableDateUtils;
import com.pnu.system.group.service.GroupSnapshotService;
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
@RequestMapping("/group/internal/snapshot")
@RequiredArgsConstructor
public class GroupSnapshotController {

    private final GroupSnapshotService service;

    @GetMapping("/modifiedAfter")
    public List<GroupSnapshotDto> getModifiedAfterDate(@RequestParam String lastModifiedAt) {
        return service.getModifiedAfterDate(TimetableDateUtils.asZonedDateTime(lastModifiedAt));
    }

    @GetMapping("/{id}")
    public GroupSnapshotDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping("/list")
    public List<GroupSnapshotDto> getByIds(@RequestBody List<String> ids) {
        return service.getByIds(ids);
    }
}
