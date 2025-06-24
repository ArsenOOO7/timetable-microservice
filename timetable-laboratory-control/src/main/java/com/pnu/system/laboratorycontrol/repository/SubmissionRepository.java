package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.laboratorycontrol.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {

    List<Submission> findAllBySubAssignmentIdAndAuthorId(String subAssignmentId, String authorId);

}
