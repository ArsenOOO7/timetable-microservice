package com.pnu.system.timetable.repository;

import com.pnu.system.timetable.domain.TimetableUserSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableUserSnapshotRepository extends JpaRepository<TimetableUserSnapshot, String> {
}
