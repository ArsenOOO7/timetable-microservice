package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.common.snapshot.provider.LessonLocationSnapshotProvider;
import com.pnu.system.lessonlocation.repository.LessonLocationSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonLocationSnapshotService implements LessonLocationSnapshotProvider {

    private final LessonLocationSnapshotRepository repository;

    @Override
    public LessonLocationSnapshotDto getById(String id) {
        return repository.getSnapshotById(id);
    }

    @Override
    public List<LessonLocationSnapshotDto> getByIds(List<String> ids) {
        return repository.getByIds(ids);
    }
}
