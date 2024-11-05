package com.pnu.system.timetable.mapper;

import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.timetable.domain.TimetableSubjectSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimetableSubjectSnapshotMapper {

    TimetableSubjectSnapshot asTimetableSubjectSnapshot(SubjectSnapshotDto userSnapshotDto);

}
