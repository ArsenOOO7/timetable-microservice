package com.pnu.system.identityaccess.service;

import com.pnu.system.common.exception.InvalidParameterException;
import com.pnu.system.identityaccess.constant.RoleType;
import com.pnu.system.identityaccess.domain.Permission;
import com.pnu.system.identityaccess.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionService {

    private static final EnumSet<RoleType> AVAILABLE_TYPES = EnumSet.of(RoleType.USER, RoleType.TEACHER, RoleType.ADMIN);

    private final PermissionRepository repository;

    public List<Permission> getByType(RoleType type) {
        if (!AVAILABLE_TYPES.contains(type)) {
            throw new InvalidParameterException("Invalid type %s".formatted(type));
        }
        return repository.getByType(type);
    }

    public List<Permission> getAll(List<String> ids) {
        return repository.findAllById(ids);
    }
}
