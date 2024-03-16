package com.pnu.system.common.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String guid, String entityName) {
        super("Entity " + entityName + " with guid " + guid + " cannot be found.");
    }
}
