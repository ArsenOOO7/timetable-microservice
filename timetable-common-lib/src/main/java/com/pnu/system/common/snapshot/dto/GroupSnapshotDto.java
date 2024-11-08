package com.pnu.system.common.snapshot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupSnapshotDto {

    private String id;
    private String name;
    private ZonedDateTime lastModifiedAt;

}
