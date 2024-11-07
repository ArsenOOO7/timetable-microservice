package com.pnu.system.common.snapshot.provider;

import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;

import java.util.List;

public interface GroupSnapshotProvider {

    GroupSnapshotDto getById(String id);

    List<GroupSnapshotDto> getByIds(List<String> ids);

}
