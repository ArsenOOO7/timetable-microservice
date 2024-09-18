package com.pnu.system.lessonlocation.repository;

import com.pnu.system.lessonlocation.domain.LessonLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonLocationRepository extends JpaRepository<LessonLocation, String> {
}
