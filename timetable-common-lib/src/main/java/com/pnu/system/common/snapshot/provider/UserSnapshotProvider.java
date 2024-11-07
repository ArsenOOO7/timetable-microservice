package com.pnu.system.common.snapshot.provider;

import com.pnu.system.common.snapshot.dto.UserSnapshotDto;

import java.util.List;

public interface UserSnapshotProvider {

    UserSnapshotDto getById(String id);

    List<UserSnapshotDto> getByIds(List<String> ids);

}
