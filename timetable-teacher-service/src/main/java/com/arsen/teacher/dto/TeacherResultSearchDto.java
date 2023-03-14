package com.arsen.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class TeacherResultSearchDto {

    private long id;

    private String firstName;
    private String lastName;
    private String fatherName;

}
