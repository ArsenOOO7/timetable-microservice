package com.pnu.system.timetable.service;

import com.pnu.system.common.messaging.service.MessagingSnapshotService;
import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.timetable.domain.TimetableGroupSnapshot;
import com.pnu.system.timetable.mapper.TimetableGroupSnapshotMapper;
import com.pnu.system.timetable.repository.TimetableGroupSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableGroupSnapshotService implements MessagingSnapshotService<GroupSnapshotDto> {

    private final TimetableRestClient restClient;
    private final TimetableGroupSnapshotMapper mapper;
    private final TimetableGroupSnapshotRepository repository;

    @Value("${baseUrl.group}/group/internal/snapshot/modifiedAfter")
    private String groupsModifiedAfterUrl;

    @Scheduled(initialDelay = 1000 * 30, fixedDelay = Long.MAX_VALUE)
    public void synchronize() {
        //TODO 2/1/25: Need to implement Lock here
        try {
            ZonedDateTime latestModifiedDate = repository.getLatestModifiedDate()
                    .orElse(ZonedDateTime.ofInstant(Instant.EPOCH, TimeZone.getDefault().toZoneId()));
            Map<String, Object> queryParams = Map.of("lastModifiedAt", latestModifiedDate);
            List<GroupSnapshotDto> received = restClient.getList(groupsModifiedAfterUrl, queryParams, GroupSnapshotDto[].class);
            repository.saveAll(received.stream().map(mapper::asTimetableGroupSnapshot).toList());
            log.info("Received Group Snapshots with ids: {}", received.stream().map(GroupSnapshotDto::getId).collect(Collectors.joining(", ")));
        } catch (Exception e) {
            log.error("Error while synchronizing Group Snapshots.", e);
        }
    }

    public List<TimetableGroupSnapshot> getByIds(Collection<String> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void receiveSnapshot(GroupSnapshotDto snapshot) {
        TimetableGroupSnapshot user = mapper.asTimetableGroupSnapshot(snapshot);
        repository.save(user);
        log.trace("User snapshot received {}.", user.getId());
    }

    @Override
    public void markAsDeleted(String id) {
        repository.findById(id)
                .ifPresentOrElse(group -> {
                    group.setDeleted(true);
                    repository.save(group);
                    log.trace("Group {} marked as delete.", group.getId());
                }, () -> log.trace("Cannot find group with id {}.", id));
    }
}
