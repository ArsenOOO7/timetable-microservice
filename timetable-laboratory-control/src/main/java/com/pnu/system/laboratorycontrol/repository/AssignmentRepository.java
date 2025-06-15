package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.laboratorycontrol.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {
}
