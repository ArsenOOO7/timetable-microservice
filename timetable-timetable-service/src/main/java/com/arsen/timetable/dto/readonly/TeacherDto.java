package com.arsen.timetable.dto.readonly;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class TeacherDto {

    private long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String meetingLink;

}
