package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.laboratorycontrol.domain.SubAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubAssignmentRepository extends JpaRepository<SubAssignment, String> {

    List<SubAssignment> findAllByAssignmentId(String assignmentId);

    @Modifying
    void deleteAllByAssignmentId(String assignmentId);

}
