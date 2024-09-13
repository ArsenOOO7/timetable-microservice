package com.pnu.system.identityaccess.mapper;

import com.pnu.system.common.constant.PermissionName;
import com.pnu.system.identityaccess.api.dto.UserTokenResponse;
import com.pnu.system.identityaccess.api.dto.UserWhoamiResponseDto;
import com.pnu.system.identityaccess.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    UserWhoamiResponseDto asUserWhoamiResponse(User user, List<PermissionName> permissions);

    UserTokenResponse asUserTokenResponse(String token);

}
