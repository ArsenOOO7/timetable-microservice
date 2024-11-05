package com.pnu.system.timetable.service;

import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.service.MessagingSnapshotService;
import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.timetable.domain.TimetableGroupSnapshot;
import com.pnu.system.timetable.mapper.TimetableGroupSnapshotMapper;
import com.pnu.system.timetable.repository.TimetableGroupSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableGroupSnapshotService implements MessagingSnapshotService<GroupSnapshotDto> {

    private final TimetableGroupSnapshotMapper mapper;
    private final TimetableGroupSnapshotRepository repository;

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

    @Override
    public boolean supports(EntityMessageType type) {
        return EntityMessageType.GROUP.equals(type);
    }
}
