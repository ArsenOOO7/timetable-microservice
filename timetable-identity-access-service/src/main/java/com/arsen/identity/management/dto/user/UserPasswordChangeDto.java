package com.arsen.identity.management.dto.user;

import com.arsen.identity.management.validation.annotation.FieldsMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldsMatch(field = "newPassword", fieldMatch = "repeatPassword", message = "Passwords do not match!")
public class UserPasswordChangeDto {

    private String oldPassword;
    private String repeatPassword;
    private String newPassword;

}
