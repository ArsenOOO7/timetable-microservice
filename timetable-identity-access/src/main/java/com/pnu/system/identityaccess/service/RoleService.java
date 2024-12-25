package com.pnu.system.identityaccess.service;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.common.search.dto.BaseSearchRequest;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.identityaccess.api.dto.RoleCreateRequest;
import com.pnu.system.identityaccess.api.dto.RolePreviewDto;
import com.pnu.system.identityaccess.api.dto.RoleResponseDto;
import com.pnu.system.identityaccess.api.dto.RoleUpdateRequest;
import com.pnu.system.identityaccess.domain.Permission;
import com.pnu.system.identityaccess.domain.Role;
import com.pnu.system.identityaccess.mapper.RoleMapper;
import com.pnu.system.identityaccess.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService extends AbstractPersistenceService<Role> {

    private final RoleMapper mapper;
    private final RoleRepository repository;
    private final PermissionService permissionService;

    public RoleResponseDto create(RoleCreateRequest request) {
        Role role = mapper.asRole(request);
        assignPermissionsToRole(role, request.getPermissionIds());
        return mapper.asRoleResponseDto(super.create(role));
    }

    public RoleResponseDto update(RoleUpdateRequest request) {
        Role role = mapper.asRole(request);
        assignPermissionsToRole(role, request.getPermissionIds());
        return mapper.asRoleResponseDto(super.update(role));
    }

    public RoleResponseDto getById(String id) {
        return mapper.asRoleResponseDto(repository.getRoleById(id));
    }

    public List<RolePreviewDto> getList(BaseSearchRequest request) {
        return mapper.asRolePreviewDtos(repository.getList(request));
    }

    public List<PermissionName> getPermissionNamesByRoleIds(List<String> roleIds) {
        return repository.getPermissionNamesByRoleIds(roleIds);
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    private void assignPermissionsToRole(Role role, List<String> permissionIds) {
        List<Permission> permissions = permissionService.getAll(permissionIds);
        role.setPermissions(permissions);
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
