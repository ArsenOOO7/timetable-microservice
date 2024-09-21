package com.pnu.system.identityaccess.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.identityaccess.domain.TeacherProfile;
import com.pnu.system.identityaccess.repository.TeacherProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeacherProfileService extends AbstractPersistenceService<TeacherProfile> {

    private final TeacherProfileRepository repository;

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
