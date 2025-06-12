package com.pnu.system.timetable.service;

import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.messaging.service.MessagingSnapshotService;
import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.timetable.domain.TimetableSubjectSnapshot;
import com.pnu.system.timetable.mapper.TimetableSubjectSnapshotMapper;
import com.pnu.system.timetable.repository.TimetableSubjectSnapshotRepository;
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
public class TimetableSubjectSnapshotService implements MessagingSnapshotService<SubjectSnapshotDto> {

    private final TimetableRestClient restClient;
    private final TimetableSubjectSnapshotMapper mapper;
    private final TimetableSubjectSnapshotRepository repository;

    @Value("${baseUrl.academic_catalog}/subject/internal/snapshot/modifiedAfter")
    private String subjectsModifiedAfterUrl;

    @Override
    @Scheduled(initialDelay = 1000 * 30, fixedDelay = Long.MAX_VALUE)
    public void synchronize() {
        //TODO 2/1/25: Need to implement Lock here
        try {
            ZonedDateTime latestModifiedDate = repository.getLatestModifiedDate()
                    .orElse(ZonedDateTime.ofInstant(Instant.EPOCH, TimeZone.getDefault().toZoneId()));
            Map<String, Object> queryParams = Map.of("lastModifiedAt", latestModifiedDate);
            List<SubjectSnapshotDto> received = restClient.getList(subjectsModifiedAfterUrl, queryParams, SubjectSnapshotDto[].class);
            repository.saveAll(received.stream().map(mapper::asTimetableSubjectSnapshot).toList());
            log.info("Received Subject Snapshots with ids: {}", received.stream().map(SubjectSnapshotDto::getId).collect(Collectors.joining(", ")));
        } catch (Exception e) {
            log.error("Error while synchronizing Subject Snapshots.", e);
        }
    }

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
}
