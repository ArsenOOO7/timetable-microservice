package com.pnu.system.api.dto;

import com.pnu.system.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWhoamiResponseDto extends User { // mb use this 😥😥😥

    private List<String> permissions;

}
