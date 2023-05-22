package com.arsen.identity.management.exception;

import com.arsen.common.exception.TimetableException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends TimetableException {
    public InvalidTokenException() {
        super(HttpStatus.FORBIDDEN);
    }

    public InvalidTokenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
