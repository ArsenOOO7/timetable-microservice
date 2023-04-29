package com.arsen.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class GroupDto {

    private String cypher;

    private short academicYear;
    private short number;

    private boolean master;
    private boolean college;
    private boolean collective;

    private Set<Long> groupIds;

}
