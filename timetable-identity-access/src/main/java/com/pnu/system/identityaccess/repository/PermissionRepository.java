package com.pnu.system.identityaccess.repository;

import com.pnu.system.identityaccess.constant.RoleType;
import com.pnu.system.identityaccess.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    List<Permission> getByType(RoleType type);

}
