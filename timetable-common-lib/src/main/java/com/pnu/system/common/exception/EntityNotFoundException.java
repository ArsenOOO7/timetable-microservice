package com.pnu.system.common.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id, String entityName) {
        super("Entity " + entityName + " with id " + id + " cannot be found.");
    }
}
