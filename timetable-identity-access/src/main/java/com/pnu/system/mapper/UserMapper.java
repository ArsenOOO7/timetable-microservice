package com.pnu.system.mapper;

import com.pnu.system.api.dto.UserCreateDto;
import com.pnu.system.common.domain.UserJWTDetails;
import com.pnu.system.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toUser(UserCreateDto userCreateDto);

    UserJWTDetails toBaseUserDetails(User user);

}
