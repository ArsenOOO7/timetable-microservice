package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.file.storage.domain.BaseAttachmentEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "submission_attachment")
public class SubmissionAttachment extends BaseAttachmentEntity {
}
