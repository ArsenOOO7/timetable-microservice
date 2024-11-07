package com.pnu.system.timetable.mapper;

import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.timetable.domain.TimetableGroupSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimetableGroupSnapshotMapper {

    TimetableGroupSnapshot asTimetableGroupSnapshot(GroupSnapshotDto userSnapshotDto);

}
