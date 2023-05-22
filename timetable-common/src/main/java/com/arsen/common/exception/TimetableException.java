package com.arsen.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class TimetableException extends RuntimeException{

    private final HttpStatus status;

    public TimetableException(HttpStatus status) {
        this.status = status;
    }

    public TimetableException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
