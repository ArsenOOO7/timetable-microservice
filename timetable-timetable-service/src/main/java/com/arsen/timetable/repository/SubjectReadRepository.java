package com.arsen.timetable.repository;

import com.arsen.timetable.domain.readonly.SubjectRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectReadRepository extends JpaRepository<SubjectRead, Long> {
}
