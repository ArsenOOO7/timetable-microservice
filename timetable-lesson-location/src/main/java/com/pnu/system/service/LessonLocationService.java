package com.pnu.system.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.domain.LessonLocation;
import com.pnu.system.repository.LessonLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonLocationService extends AbstractPersistenceService<LessonLocation> {

    private final LessonLocationRepository lessonLocationRepository;

    public List<LessonLocation> getAllLessonLocation() {
        return lessonLocationRepository.findAll();
    }

    @Override
    protected Class<LessonLocation> getEntityType() {
        return LessonLocation.class;
    }

    @Override
    protected JpaRepository<LessonLocation, String> getRepository() {
        return lessonLocationRepository;
    }
}
