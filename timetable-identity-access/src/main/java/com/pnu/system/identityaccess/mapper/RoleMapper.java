package com.pnu.system.identityaccess.mapper;

import com.pnu.system.identityaccess.api.dto.RoleCreateRequest;
import com.pnu.system.identityaccess.api.dto.RoleResponseDto;
import com.pnu.system.identityaccess.api.dto.RoleUpdateRequest;
import com.pnu.system.identityaccess.domain.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role asRole(RoleCreateRequest request);

    Role asRole(RoleUpdateRequest request);

    RoleResponseDto asRoleResponseDto(Role role);

}
