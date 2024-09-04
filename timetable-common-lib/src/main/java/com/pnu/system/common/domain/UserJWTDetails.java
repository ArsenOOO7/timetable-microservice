package com.pnu.system.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserJWTDetails extends BaseEntity {

    private List<String> permissions;

}
