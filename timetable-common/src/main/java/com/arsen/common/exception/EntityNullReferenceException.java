package com.arsen.common.exception;

/**
 * Exception throws when entity is null
 * @author Arsen Sydoryk
 */
public class EntityNullReferenceException extends RuntimeException{

    public EntityNullReferenceException() {
    }

    public EntityNullReferenceException(String message) {
        super(message);
    }
}

