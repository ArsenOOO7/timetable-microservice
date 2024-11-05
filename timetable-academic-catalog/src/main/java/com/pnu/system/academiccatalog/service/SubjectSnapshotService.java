package com.pnu.system.academiccatalog.service;

import com.pnu.system.academiccatalog.repository.SubjectSnapshotRepository;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.common.snapshot.provider.SubjectSnapshotProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectSnapshotService implements SubjectSnapshotProvider {

    private final SubjectSnapshotRepository repository;

    @Override
    public SubjectSnapshotDto getById(String id) {
        return repository.getSnapshotById(id);
    }

    @Override
    public List<SubjectSnapshotDto> getByIds(List<String> ids) {
        return repository.getByIds(ids);
    }
}
