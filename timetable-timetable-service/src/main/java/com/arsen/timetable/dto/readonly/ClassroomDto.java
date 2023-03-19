package com.arsen.timetable.dto.readonly;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ClassroomDto {

    private long id;
    private String name;
    private String address;

}
