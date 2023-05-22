package com.arsen.teacher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
    @NotNull
    private String fatherName;

    @Email
    private String email;

    private String meetingLink;

}
