package com.pnu.system.timetable.service;

import com.pnu.system.common.exception.EntityNotFoundException;
import com.pnu.system.common.messaging.constant.EntityMessageType;
import com.pnu.system.common.messaging.service.MessagingSnapshotService;
import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.timetable.domain.TimetableUserSnapshot;
import com.pnu.system.timetable.mapper.TimetableUserSnapshotMapper;
import com.pnu.system.timetable.repository.TimetableUserSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableUserSnapshotService implements MessagingSnapshotService<UserSnapshotDto> {

    private final TimetableRestClient restClient;
    private final TimetableUserSnapshotMapper mapper;
    private final TimetableUserSnapshotRepository repository;

    @Value("${baseUrl.identity_access}/user/{id}/internal/group/list")
    private String userGroupIdsUrl;

    public TimetableUserSnapshot getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "User"));
    }

    //TODO 11/4/24: Refactor...
    public List<String> getGroupIdListByUserId(String userId) {
        return restClient.getList(new UriTemplate(userGroupIdsUrl).expand(userId).toString(), String[].class);
    }

    @Override
    public void receiveSnapshot(UserSnapshotDto snapshot) {
        TimetableUserSnapshot user = mapper.asTimetableUserSnapshot(snapshot);
        repository.save(user);
        log.trace("User snapshot received {}.", user.getId());
    }

    @Override
    public void markAsDeleted(String id) {
        repository.findById(id)
                .ifPresentOrElse(user -> {
                    user.setDeleted(true);
                    repository.save(user);
                    log.trace("User {} marked as delete.", user.getId());
                }, () -> log.trace("Cannot find user with id {}.", id));
    }

    @Override
    public boolean supports(EntityMessageType type) {
        return EntityMessageType.USER.equals(type);
    }
}
