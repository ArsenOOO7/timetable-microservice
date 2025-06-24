package com.pnu.system.file.storage.dto;

import com.pnu.system.common.dto.VersionDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentDto extends VersionDto {

    private String primaryObjectGuid;
    private String link;
    private String originalFilename;
    private String fileUri;

}
