package com.pnu.system.repository;

import com.pnu.system.domain.LessonLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonLocationRepository extends JpaRepository<LessonLocation, String> {
}
