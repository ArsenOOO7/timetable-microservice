package com.pnu.system.group.repository;

import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.group.domain.Group;
import com.pnu.system.group.domain.QGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    QGroup qGroup = QGroup.group;

    boolean existsByParentId(String id);

    default List<Group> getList(BaseSearchRequest request) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qGroup)
                .leftJoin(qGroup.groupCategoryIds)
                .leftJoin(qGroup.relatedGroupIds)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }

}
