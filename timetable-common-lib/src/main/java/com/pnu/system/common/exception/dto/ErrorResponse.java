package com.pnu.system.common.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private String message;
    private List<String> errors;
    private List<ErrorField> fields;

}
