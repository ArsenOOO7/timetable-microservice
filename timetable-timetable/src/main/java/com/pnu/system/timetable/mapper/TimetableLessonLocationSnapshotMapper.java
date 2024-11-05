package com.pnu.system.timetable.mapper;

import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.timetable.domain.TimetableLessonLocationSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimetableLessonLocationSnapshotMapper {

    TimetableLessonLocationSnapshot asTimetableLessonLocationSnapshot(LessonLocationSnapshotDto userSnapshotDto);

}
