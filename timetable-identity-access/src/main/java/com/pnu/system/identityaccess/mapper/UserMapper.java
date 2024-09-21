package com.pnu.system.identityaccess.mapper;

import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import com.pnu.system.identityaccess.api.dto.UserResponseDto;
import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User asUser(UserCreateRequest userCreateRequest);

    User asUser(UserUpdateRequest userUpdateRequest);

    UserResponseDto asUserResponseDto(User user);
}
