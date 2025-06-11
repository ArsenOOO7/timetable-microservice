package com.pnu.system.file.storage.domain;

import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseAttachmentEntity extends AuditableEntity {

    @Column(name = "primary_object_guid")
    private String primaryObjectGuid;
    @Column(name = "link")
    private String link;
    @Column(name = "original_filename")
    private String originalFilename;
    @Column(name = "file_uri")
    private String fileUri;

}
