package com.pnu.system.identityaccess.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.common.search.utils.ConditionUtils;
import com.pnu.system.common.security.constant.SecurityConstants;
import com.pnu.system.common.utils.TimetableUserUtils;
import com.pnu.system.identityaccess.domain.UserSearch;
import com.pnu.system.identityaccess.repository.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserSearchService extends AbstractSearchService<UserSearch> {

    private final UserSearchRepository repository;

    @Override
    public List<Map<String, Object>> search(ReportSearchRequest request) {
        request.getConditions().add(ConditionUtils.build("id", SecurityConstants.INTERNAL_USER_ID, ConditionOperation.NOT_EQUAL));
        if (!SecurityConstants.SUPER_ADMIN_USER_ID.equals(TimetableUserUtils.getId())) {
            request.getConditions().add(ConditionUtils.build("id", SecurityConstants.SUPER_ADMIN_USER_ID, ConditionOperation.NOT_EQUAL));
        }
        return super.search(request);
    }

    @Override
    protected AbstractSearchRepository<UserSearch> getRepository() {
        return repository;
    }
}
