package com.pnu.system.common.exception;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Collections;
import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final String messageCode;
    private final Object[] messageArgs;
    private final String defaultMessage;
    private final List<DefaultMessageSourceResolvable> errors;

    public ValidationException(String messageCode, Object[] messageArgs, String defaultMessage) {
        this(messageCode, messageArgs, defaultMessage, Collections.emptyList());
    }

    public ValidationException(String messageCode, Object[] messageArgs, String defaultMessage, List<DefaultMessageSourceResolvable> errors) {
        this.messageCode = messageCode;
        this.messageArgs = messageArgs;
        this.defaultMessage = defaultMessage;
        this.errors = errors;
    }

    public ValidationException(String messageCode, String defaultMessage) {
        this(messageCode, null, defaultMessage);
    }

    public ValidationException(String messageCode, Object[] messageArgs) {
        this(messageCode, messageArgs, null);
    }

    public ValidationException(String messageCode, List<DefaultMessageSourceResolvable> errors) {
        this(messageCode, null, null, errors);
    }
}
