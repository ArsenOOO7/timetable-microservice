package com.pnu.system.common.snapshot.provider;

import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;

import java.util.List;

public interface SubjectSnapshotProvider {

    SubjectSnapshotDto getById(String id);

    List<SubjectSnapshotDto> getByIds(List<String> ids);

}
