package com.pnu.system.identityaccess.mapper;

import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import com.pnu.system.identityaccess.api.dto.UserDto;
import com.pnu.system.identityaccess.api.dto.UserResponseDto;
import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.domain.User;
import com.pnu.system.identityaccess.domain.document.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User asUser(UserCreateRequest userCreateRequest);

    @Mapping(target = "id", ignore = true)
    void applyUserUpdateRequest(@MappingTarget User user, UserUpdateRequest request);

    UserResponseDto asUserResponseDto(User user);

    List<UserDto> asUserDtos(List<UserDocument> documents);

}
