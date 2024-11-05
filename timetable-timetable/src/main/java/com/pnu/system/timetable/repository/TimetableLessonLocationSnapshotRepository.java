package com.pnu.system.timetable.repository;

import com.pnu.system.timetable.domain.TimetableLessonLocationSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableLessonLocationSnapshotRepository extends JpaRepository<TimetableLessonLocationSnapshot, String> {
}
