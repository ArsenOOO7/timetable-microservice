package com.arsen.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception throws when entity is null
 * @author Arsen Sydoryk
 */
public class EntityNullReferenceException extends TimetableException{

    public EntityNullReferenceException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public EntityNullReferenceException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

