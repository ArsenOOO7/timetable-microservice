package com.pnu.system.identityaccess.service;

import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.common.snapshot.provider.UserSnapshotProvider;
import com.pnu.system.identityaccess.repository.UserSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSnapshotService implements UserSnapshotProvider {

    private final UserSnapshotRepository repository;

    @Override
    public UserSnapshotDto getById(String id) {
        return repository.getSnapshotById(id);
    }

    @Override
    public List<UserSnapshotDto> getByIds(List<String> ids) {
        return repository.getByIds(ids);
    }
}
