package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.constant.RoleType;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.identityaccess.domain.Permission;
import com.pnu.system.identityaccess.domain.QPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    QPermission qPermission = QPermission.permission;

    List<Permission> getByType(RoleType type);

    default List<String> getIdsByPermissionIdsAndRoleType(RoleType type, List<String> ids) {
        return QueryDslFactory.getQueryFactory()
                .select(qPermission.id)
                .from(qPermission)
                .where(qPermission.type.eq(type), qPermission.id.in(ids))
                .fetch();
    }

}
