package com.pnu.system.timetable.repository;

import com.pnu.system.timetable.domain.TimetableSubjectSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableSubjectSnapshotRepository extends JpaRepository<TimetableSubjectSnapshot, String> {
}
