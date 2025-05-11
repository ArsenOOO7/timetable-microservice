package com.pnu.system.group.repository;

import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.group.domain.Group;
import com.pnu.system.group.domain.QGroup;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    QGroup qGroup = QGroup.group;

    boolean existsByParentId(String id);

    default boolean existsByName(String name, String id) {
        BooleanBuilder predicate = new BooleanBuilder(qGroup.name.equalsIgnoreCase(name));
        if (StringUtils.isNotBlank(id)) {
            predicate.and(qGroup.id.ne(id));
        }
        return QueryDslFactory.getQueryFactory()
                .select(qGroup.count())
                .from(qGroup)
                .where(predicate)
                .fetchOne() > 0;
    }
}
