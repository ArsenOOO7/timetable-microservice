package com.pnu.system.common.snapshot.provider;

import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;

import java.util.List;

public interface LessonLocationSnapshotProvider {

    LessonLocationSnapshotDto getById(String id);

    List<LessonLocationSnapshotDto> getByIds(List<String> ids);

}
