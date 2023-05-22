package com.arsen.group.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class GroupDto {

    @NotBlank
    private String cypher;

    @Min(1)
    private short number;

    @Min(1)
    private short academicYear;

    private boolean master;
    private boolean college;
    private boolean collective;

    private Set<Long> groupIds;

}
