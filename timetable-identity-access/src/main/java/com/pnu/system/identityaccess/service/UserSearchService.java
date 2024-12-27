package com.pnu.system.identityaccess.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.identityaccess.domain.UserSearch;
import com.pnu.system.identityaccess.repository.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSearchService extends AbstractSearchService<UserSearch> {

    private final UserSearchRepository repository;

    @Override
    protected AbstractSearchRepository<UserSearch> getRepository() {
        return repository;
    }
}
