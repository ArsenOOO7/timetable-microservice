package com.pnu.system.identityaccess.service;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.identityaccess.domain.Role;
import com.pnu.system.identityaccess.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService extends AbstractPersistenceService<Role> {

    private final RoleRepository repository;

    public List<PermissionName> getPermissionNamesByRoleIds(List<String> roleIds) {
        return repository.getPermissionNamesByRoleIds(roleIds);
    }

    @Override
    protected Class<Role> getEntityType() {
        return Role.class;
    }

    @Override
    protected JpaRepository<Role, String> getRepository() {
        return repository;
    }
}
