package com.pnu.system.lessonlocation.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.lessonlocation.domain.LessonLocation;
import com.pnu.system.lessonlocation.repository.LocationSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonLocationSearchService extends AbstractSearchService<LessonLocation> {

    private final LocationSearchRepository repository;

    @Override
    protected AbstractSearchRepository<LessonLocation> getRepository() {
        return repository;
    }
}
