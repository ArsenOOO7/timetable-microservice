package com.pnu.system.lessonlocation.repository;

import com.pnu.system.lessonlocation.domain.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeRepository extends JpaRepository<LocationType, String> {
}
