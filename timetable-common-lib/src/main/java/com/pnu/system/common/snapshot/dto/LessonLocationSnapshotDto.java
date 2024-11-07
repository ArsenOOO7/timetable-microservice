package com.pnu.system.common.snapshot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonLocationSnapshotDto {

    private String id;
    private String typeShortName;
    private String name;
    private String address;

}
