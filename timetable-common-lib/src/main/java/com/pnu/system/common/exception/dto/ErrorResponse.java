package com.pnu.system.common.exception.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ErrorResponse {

    private String message;
    private List<String> errors;
    private List<ErrorField> fields;

}
