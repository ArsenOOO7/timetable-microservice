package com.pnu.system.group.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.group.domain.GroupSearch;
import com.pnu.system.group.repository.GroupSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupSearchService extends AbstractSearchService<GroupSearch> {

    private final GroupSearchRepository repository;

    @Override
    protected AbstractSearchRepository<GroupSearch> getRepository() {
        return repository;
    }
}
