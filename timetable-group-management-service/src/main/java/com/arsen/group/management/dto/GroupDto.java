package com.arsen.group.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private long id;
    private String fullName;
    private boolean collective;

    private Set<Long> groupIds;

}
