package com.arsen.classroom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ClassroomDto {

    @NotBlank
    private String address;

    @NotBlank
    private String name;

}
