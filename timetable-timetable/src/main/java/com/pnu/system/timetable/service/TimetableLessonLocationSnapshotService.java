package com.pnu.system.timetable.service;

import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.messaging.service.MessagingSnapshotService;
import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.timetable.domain.TimetableLessonLocationSnapshot;
import com.pnu.system.timetable.mapper.TimetableLessonLocationSnapshotMapper;
import com.pnu.system.timetable.repository.TimetableLessonLocationSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableLessonLocationSnapshotService implements MessagingSnapshotService<LessonLocationSnapshotDto> {

    private final TimetableRestClient restClient;
    private final TimetableLessonLocationSnapshotMapper mapper;
    private final TimetableLessonLocationSnapshotRepository repository;

    @Value("${baseUrl.lesson_location}/location/internal/snapshot/modifiedAfter")
    private String locationsModifiedAfterUrl;

    @Scheduled(initialDelay = 1000 * 30, fixedDelay = Long.MAX_VALUE)
    public void synchronize() {
        //TODO 2/1/25: Need to implement Lock here
        try {
            ZonedDateTime latestModifiedDate = repository.getLatestModifiedDate()
                    .orElse(ZonedDateTime.ofInstant(Instant.EPOCH, TimeZone.getDefault().toZoneId()));
            Map<String, Object> queryParams = Map.of("lastModifiedAt", latestModifiedDate);
            List<LessonLocationSnapshotDto> received = restClient.getList(locationsModifiedAfterUrl, queryParams, LessonLocationSnapshotDto[].class);
            repository.saveAll(received.stream().map(mapper::asTimetableLessonLocationSnapshot).toList());
            log.info("Received Lesson Location Snapshots with ids: {}", received.stream().map(LessonLocationSnapshotDto::getId).collect(Collectors.joining(", ")));
        } catch (Exception e) {
            log.error("Error while synchronizing Lesson Location Snapshots.", e);
        }
    }

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
}
