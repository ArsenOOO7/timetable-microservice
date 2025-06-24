package com.pnu.system.identityaccess.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.common.search.utils.ConditionUtils;
import com.pnu.system.common.security.constant.SecurityConstants;
import com.pnu.system.identityaccess.domain.RoleSearch;
import com.pnu.system.identityaccess.repository.RoleSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RoleSearchService extends AbstractSearchService<RoleSearch> {

    private final RoleSearchRepository repository;

    @Override
    public List<Map<String, Object>> search(ReportSearchRequest request) {
        request.getConditions().add(ConditionUtils.build("id", SecurityConstants.INTERNAL_USER_ROLE_ID, ConditionOperation.NOT_EQUAL));
        request.getConditions().add(ConditionUtils.build("id", SecurityConstants.SUPER_ADMIN_ROLE_ID, ConditionOperation.NOT_EQUAL));
        return super.search(request);
    }

    @Override
    protected AbstractSearchRepository<RoleSearch> getRepository() {
        return repository;
    }
}
