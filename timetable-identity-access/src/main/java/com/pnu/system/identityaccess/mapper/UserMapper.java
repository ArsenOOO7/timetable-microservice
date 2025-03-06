package com.pnu.system.identityaccess.mapper;

import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import com.pnu.system.identityaccess.api.dto.UserDto;
import com.pnu.system.identityaccess.api.dto.UserResponseDto;
import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.domain.User;
import com.pnu.system.identityaccess.domain.document.UserDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User asUser(UserCreateRequest userCreateRequest);

    User asUser(UserUpdateRequest userUpdateRequest);

    UserResponseDto asUserResponseDto(User user);

    List<UserDto> asUserDtos(List<UserDocument> documents);

}
