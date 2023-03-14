package com.arsen.common.exception;


/**
 * Exception throws when entity does not exist
 * @author Arsen Sydoryk
 */
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
