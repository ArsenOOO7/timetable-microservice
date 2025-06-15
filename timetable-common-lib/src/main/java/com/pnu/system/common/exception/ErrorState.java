package com.pnu.system.common.exception;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.ArrayList;
import java.util.List;

public final class ErrorState {

    private final List<DefaultMessageSourceResolvable> errors = new ArrayList<>();

    public void addError(String messageCode) {
        addError(messageCode, (Object[]) null);
    }

    public void addError(String code, Object[] messageArgs) {
        addError(code, messageArgs, null);
    }

    public void addError(String code, String defaultMessage) {
        addError(code, null, defaultMessage);
    }

    public void addError(String code, Object[] args, String defaultMessage) {
        DefaultMessageSourceResolvable error = new DefaultMessageSourceResolvable(new String[]{code}, args, defaultMessage);
        errors.add(error);
    }

    public boolean hasErrors() {
        return CollectionUtils.isNotEmpty(errors);
    }

    public List<DefaultMessageSourceResolvable> getErrors() {
        return errors;
    }
}
