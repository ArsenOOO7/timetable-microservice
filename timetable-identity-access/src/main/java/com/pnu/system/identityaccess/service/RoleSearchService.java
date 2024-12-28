package com.pnu.system.identityaccess.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.identityaccess.domain.RoleSearch;
import com.pnu.system.identityaccess.repository.RoleSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleSearchService extends AbstractSearchService<RoleSearch> {

    private final RoleSearchRepository repository;

    @Override
    protected AbstractSearchRepository<RoleSearch> getRepository() {
        return repository;
    }
}
