package com.pnu.system.timetable.repository;

import com.pnu.system.timetable.domain.TimetableSubjectSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface TimetableSubjectSnapshotRepository extends JpaRepository<TimetableSubjectSnapshot, String> {

    @Query("select snapshot.lastModifiedAt from TimetableSubjectSnapshot snapshot order by snapshot.lastModifiedAt desc limit 1")
    Optional<ZonedDateTime> getLatestModifiedDate();

}
