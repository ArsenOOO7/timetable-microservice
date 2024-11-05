package com.pnu.system.identityaccess.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.identityaccess.api.dto.TeacherProfileUpdateRequest;
import com.pnu.system.identityaccess.domain.TeacherProfile;
import com.pnu.system.identityaccess.event.model.TeacherProfileUpdateEvent;
import com.pnu.system.identityaccess.mapper.TeacherProfileMapper;
import com.pnu.system.identityaccess.repository.TeacherProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeacherProfileService extends AbstractPersistenceService<TeacherProfile> {

    private final TeacherProfileMapper mapper;
    private final TeacherProfileRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public TeacherProfile update(TeacherProfileUpdateRequest request) {
        TeacherProfile profile = getOne(request.getUserId());
        mapper.applyToTeacherProfile(profile, request);
        eventPublisher.publishEvent(new TeacherProfileUpdateEvent(profile));
        return super.update(profile);
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    protected Class<TeacherProfile> getEntityType() {
        return TeacherProfile.class;
    }

    @Override
    protected JpaRepository<TeacherProfile, String> getRepository() {
        return repository;
    }
}
