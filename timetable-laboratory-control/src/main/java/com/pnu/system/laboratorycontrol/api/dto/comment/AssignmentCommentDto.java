package com.pnu.system.laboratorycontrol.api.dto.comment;

import com.pnu.system.common.dto.BaseDto;
import com.pnu.system.common.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentCommentDto extends BaseDto {

    private String message;
    private UserDto author;

}
