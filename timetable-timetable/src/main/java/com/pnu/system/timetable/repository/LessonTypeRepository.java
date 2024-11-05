package com.pnu.system.timetable.repository;

import com.pnu.system.timetable.domain.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTypeRepository extends JpaRepository<LessonType, String> {
}
