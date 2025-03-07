package com.pnu.system.file.storage.exception;

public class StorageResourceException extends RuntimeException {

    public StorageResourceException(String message) {
        super(message);
    }

    public StorageResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
