package com.arsen.common.exception;


import org.springframework.http.HttpStatus;

/**
 * Exception throws when entity does not exist
 * @author Arsen Sydoryk
 */
public class EntityNotFoundException extends TimetableException{

    public EntityNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
