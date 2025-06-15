package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.common.search.utils.ConditionUtils;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentSearchRequest;
import com.pnu.system.laboratorycontrol.domain.AssignmentSearch;
import com.pnu.system.laboratorycontrol.repository.AssignmentSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AssignmentSearchService extends AbstractSearchService<AssignmentSearch> {

    private final AssignmentSearchRepository repository;

    public List<Map<String, Object>> search(AssignmentSearchRequest request) {
        //TODO 6/15/25: Implement Nested Condition in Search, and take into account VISIBILITY (or just move it to the normal repository...)
        request.getConditions().add(ConditionUtils.build("courseId", request.getCourseId()));
        return super.search(request);
    }

    @Override
    protected AbstractSearchRepository<AssignmentSearch> getRepository() {
        return repository;
    }
}
