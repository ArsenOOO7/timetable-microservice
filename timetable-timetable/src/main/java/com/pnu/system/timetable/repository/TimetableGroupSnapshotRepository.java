package com.pnu.system.timetable.repository;

import com.pnu.system.timetable.domain.TimetableGroupSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableGroupSnapshotRepository extends JpaRepository<TimetableGroupSnapshot, String> {
}
