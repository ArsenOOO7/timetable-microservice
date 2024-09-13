package com.pnu.system.identityaccess.repository;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.utils.QueryDslFactory;
import com.pnu.system.identityaccess.domain.QPermission;
import com.pnu.system.identityaccess.domain.QRole;
import com.pnu.system.identityaccess.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    QRole qRole = QRole.role;
    QPermission qPermission = QPermission.permission;

    default List<PermissionName> getPermissionNamesByRoleIds(List<String> roleIds) {
        return QueryDslFactory.getQueryFactory()
                .select(qPermission.name)
                .from(qRole)
                .innerJoin(qRole.permissions, qPermission)
                .where(qRole.id.in(roleIds))
                .fetch();
    }
}
