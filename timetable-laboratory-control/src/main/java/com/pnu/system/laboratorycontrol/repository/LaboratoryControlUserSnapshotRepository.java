package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.laboratorycontrol.domain.LaboratoryControlUserSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface LaboratoryControlUserSnapshotRepository extends JpaRepository<LaboratoryControlUserSnapshot, String> {

    @Query("select snapshot.lastModifiedAt from LaboratoryControlUserSnapshot snapshot order by snapshot.lastModifiedAt desc limit 1")
    Optional<ZonedDateTime> getLatestModifiedDate();

}
