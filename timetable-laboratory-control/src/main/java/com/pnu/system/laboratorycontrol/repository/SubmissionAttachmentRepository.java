package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.laboratorycontrol.domain.SubmissionAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionAttachmentRepository extends JpaRepository<SubmissionAttachment, String> {

    List<SubmissionAttachment> findAllByPrimaryObjectGuid(String primaryObjectGuid);

}
