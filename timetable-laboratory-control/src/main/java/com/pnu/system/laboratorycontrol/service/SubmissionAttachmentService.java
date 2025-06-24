package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.file.storage.constant.Folders;
import com.pnu.system.file.storage.dto.AttachmentDto;
import com.pnu.system.file.storage.model.StorageResourceWrapper;
import com.pnu.system.file.storage.service.FileStorageService;
import com.pnu.system.file.storage.utils.StorageResourcePathUtil;
import com.pnu.system.file.storage.utils.StorageResourceUtil;
import com.pnu.system.laboratorycontrol.domain.Submission;
import com.pnu.system.laboratorycontrol.domain.SubmissionAttachment;
import com.pnu.system.laboratorycontrol.mapper.SubmissionAttachmentMapper;
import com.pnu.system.laboratorycontrol.repository.SubmissionAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionAttachmentService extends AbstractPersistenceService<SubmissionAttachment> {

    private final SubmissionAttachmentMapper mapper;
    private final SubmissionAttachmentRepository repository;
    private final SubmissionService submissionService;
    private final FileStorageService storageService;

    //TODO 6/25/25: MOVE ALL BASE METHODS TO FILE-STORAGE LIB
    public AttachmentDto createNew(String subAssignmentId, MultipartFile file) {
        Submission submission = submissionService.create(subAssignmentId);
        return create(submission.getId(), file);
    }

    public AttachmentDto createForExisting(String submissionId, MultipartFile file) {
        return create(submissionId, file);
    }

    private AttachmentDto create(String submissionId, MultipartFile file) {
        String uri = StorageResourcePathUtil.buildUuidUri(file.getOriginalFilename(), Folders.SUBMISSIONS_FOLDER);
        SubmissionAttachment attachment = mapper.asSubmissionAttachment(submissionId, file.getOriginalFilename(), uri);
        storageService.upload(file, uri);
        return mapper.asAttachmentDto(super.create(attachment));
    }

    public List<AttachmentDto> getList(String submissionId) {
        return mapper.asAttachmentDtos(repository.findAllByPrimaryObjectGuid(submissionId));
    }

    public ResponseEntity<InputStreamResource> download(String id) {
        SubmissionAttachment attachment = getOne(id);
        StorageResourceWrapper object = storageService.download(attachment.getFileUri());
        return StorageResourceUtil.asResponse(object);
    }

    @Override
    public void delete(SubmissionAttachment entity) {
        super.delete(entity);
        storageService.deleteFile(entity.getFileUri());
    }

    @Override
    protected Class<SubmissionAttachment> getEntityType() {
        return SubmissionAttachment.class;
    }

    @Override
    protected JpaRepository<SubmissionAttachment, String> getRepository() {
        return repository;
    }
}
