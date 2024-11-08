package com.pnu.system.group.service;

import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.common.snapshot.provider.GroupSnapshotProvider;
import com.pnu.system.group.repository.GroupSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupSnapshotService implements GroupSnapshotProvider {

    private final GroupSnapshotRepository repository;

    public List<GroupSnapshotDto> getModifiedAfterDate(ZonedDateTime lastModifiedAt) {
        return repository.getModifiedAfterDate(lastModifiedAt);
    }

    @Override
    public GroupSnapshotDto getById(String id) {
        return repository.getSnapshotById(id);
    }

    @Override
    public List<GroupSnapshotDto> getByIds(List<String> ids) {
        return repository.getByIds(ids);
    }
}
