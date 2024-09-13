package com.pnu.system.identityaccess.mapper;

import com.pnu.system.identityaccess.api.dto.UserCreateDto;
import com.pnu.system.identityaccess.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User asUser(UserCreateDto userCreateDto);


}
