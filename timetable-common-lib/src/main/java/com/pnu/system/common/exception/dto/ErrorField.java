package com.pnu.system.common.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorField {

    private String fieldName;
    private String message;

}
