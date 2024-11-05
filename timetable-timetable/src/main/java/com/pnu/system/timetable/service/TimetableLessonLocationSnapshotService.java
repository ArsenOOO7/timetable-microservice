package com.pnu.system.timetable.service;

import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.service.MessagingSnapshotService;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.timetable.domain.TimetableLessonLocationSnapshot;
import com.pnu.system.timetable.mapper.TimetableLessonLocationSnapshotMapper;
import com.pnu.system.timetable.repository.TimetableLessonLocationSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableLessonLocationSnapshotService implements MessagingSnapshotService<LessonLocationSnapshotDto> {

    private final TimetableLessonLocationSnapshotMapper mapper;
    private final TimetableLessonLocationSnapshotRepository repository;

    public TimetableLessonLocationSnapshot getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "Location"));
    }

    @Override
    public void receiveSnapshot(LessonLocationSnapshotDto snapshot) {
        TimetableLessonLocationSnapshot user = mapper.asTimetableLessonLocationSnapshot(snapshot);
        repository.save(user);
        log.trace("User snapshot received {}.", user.getId());
    }

    @Override
    public void markAsDeleted(String id) {
        repository.findById(id)
                .ifPresentOrElse(subject -> {
                    subject.setDeleted(true);
                    repository.save(subject);
                    log.trace("Lesson Location {} marked as delete.", subject.getId());
                }, () -> log.trace("Cannot find lesson location with id {}.", id));
    }

    @Override
    public boolean supports(EntityMessageType type) {
        return EntityMessageType.LESSON_LOCATION.equals(type);
    }
}
