package com.arsen.identity.management.exception;

import com.arsen.common.exception.TimetableException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends TimetableException {

    public InvalidCredentialsException() {
        super(HttpStatus.FORBIDDEN);
    }

    public InvalidCredentialsException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
