package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.laboratorycontrol.domain.LaboratoryControlGroupSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface LaboratoryControlGroupSnapshotRepository extends JpaRepository<LaboratoryControlGroupSnapshot, String> {

    @Query("select snapshot.lastModifiedAt from LaboratoryControlGroupSnapshot snapshot order by snapshot.lastModifiedAt desc limit 1")
    Optional<ZonedDateTime> getLatestModifiedDate();

}
