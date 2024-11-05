package com.pnu.system.common.snapshot.dto;

import com.pnu.system.common.constant.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSnapshotDto {

    private String id;
    private String firstName;
    private String lastName;
    private String personalLink;
    private UserType type;

}
