package com.pnu.system.timetable.mapper;

import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.timetable.domain.TimetableUserSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimetableUserSnapshotMapper {

    TimetableUserSnapshot asTimetableUserSnapshot(UserSnapshotDto userSnapshotDto);

}
