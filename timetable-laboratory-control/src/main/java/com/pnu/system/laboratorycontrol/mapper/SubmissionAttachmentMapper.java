package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.file.storage.dto.AttachmentDto;
import com.pnu.system.laboratorycontrol.domain.SubmissionAttachment;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SubmissionAttachmentMapper {

    //TODO 6/25/25: Move to File Storage lib
    default SubmissionAttachment asSubmissionAttachment(String primaryObjectGuid, String originalFileName, String fileUri) {
        return asSubmissionAttachment(primaryObjectGuid, originalFileName, fileUri, null);
    }

    SubmissionAttachment asSubmissionAttachment(String primaryObjectGuid, String originalFileName, String fileUri, String link);

    AttachmentDto asAttachmentDto(SubmissionAttachment submissionAttachment);

    List<AttachmentDto> asAttachmentDtos(Collection<SubmissionAttachment> submissionAttachments);

}
