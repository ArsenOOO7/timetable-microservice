package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.search.AbstractSearchRepository;
import com.pnu.system.common.search.AbstractSearchService;
import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.constant.DataType;
import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.common.search.dto.SearchCondition;
import com.pnu.system.common.utils.UserUtils;
import com.pnu.system.laboratorycontrol.domain.CourseSearch;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlUserSnapshot;
import com.pnu.system.laboratorycontrol.repository.CourseSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CourseSearchService extends AbstractSearchService<CourseSearch> {

    private static final String COURSE_AUTHORS_FIELD = "course.authors.id";
    private static final String COURSE_GROUPS_FIELD = "course.groups.id";

    private final CourseSearchRepository repository;
    private final LaboratoryControlUserSnapshotService userSnapshotService;

    @Override
    public List<Map<String, Object>> search(ReportSearchRequest request) {
        String userId = UserUtils.getId();

        //TODO ARSEN 5/17/25: Temporary, refactor
        LaboratoryControlUserSnapshot user = userSnapshotService.getById(userId);
        switch (user.getType()) {
            case USER -> {
                List<String> groupIds = userSnapshotService.getGroupIdListByUserId(userId);
                SearchCondition condition = SearchCondition.builder()
                        .fieldName(COURSE_GROUPS_FIELD)
                        .value(groupIds)
                        .dataType(DataType.LIST_STRING)
                        .operation(ConditionOperation.IN)
                        .collectionField(true)
                        .build();
                request.getConditions().add(condition);
            }
            case TEACHER -> {
                SearchCondition condition = SearchCondition.builder()
                        .fieldName(COURSE_AUTHORS_FIELD)
                        .value(user)
                        .dataType(DataType.STRING)
                        .operation(ConditionOperation.EQUAL)
                        .collectionField(true)
                        .build();
                request.getConditions().add(condition);
            }
        }
        return super.search(request);
    }

    @Override
    protected AbstractSearchRepository<CourseSearch> getRepository() {
        return repository;
    }
}
