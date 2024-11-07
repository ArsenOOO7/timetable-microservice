package com.pnu.system.timetable.service;

import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.service.MessagingSnapshotService;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.timetable.domain.TimetableSubjectSnapshot;
import com.pnu.system.timetable.mapper.TimetableSubjectSnapshotMapper;
import com.pnu.system.timetable.repository.TimetableSubjectSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableSubjectSnapshotService implements MessagingSnapshotService<SubjectSnapshotDto> {

    private final TimetableSubjectSnapshotMapper mapper;
    private final TimetableSubjectSnapshotRepository repository;

    public TimetableSubjectSnapshot getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "Subject"));
    }

    @Override
    public void receiveSnapshot(SubjectSnapshotDto snapshot) {
        TimetableSubjectSnapshot user = mapper.asTimetableSubjectSnapshot(snapshot);
        repository.save(user);
        log.trace("User snapshot received {}.", user.getId());
    }

    @Override
    public void markAsDeleted(String id) {
        repository.findById(id)
                .ifPresentOrElse(subject -> {
                    subject.setDeleted(true);
                    repository.save(subject);
                    log.trace("Subject {} marked as delete.", subject.getId());
                }, () -> log.trace("Cannot find subject with id {}.", id));
    }

    @Override
    public boolean supports(EntityMessageType type) {
        return EntityMessageType.SUBJECT.equals(type);
    }
}
