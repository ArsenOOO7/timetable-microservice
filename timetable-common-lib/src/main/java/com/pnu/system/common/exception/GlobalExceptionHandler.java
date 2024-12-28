package com.pnu.system.common.exception;

import com.pnu.system.common.exception.dto.ErrorField;
import com.pnu.system.common.exception.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationException(ValidationException e) {
        String message = messageSource.getMessage(e.getMessageCode(), e.getMessageArgs(), e.getDefaultMessage(), Locale.getDefault());
        log.error(message);
        return ErrorResponse.builder()
                .message(message)
                .build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationException(BindException e) {
        log.error(e.getMessage());
        List<String> errors = e.getGlobalErrors().stream()
                .map(error -> messageSource.getMessage(error, Locale.getDefault()))
                .toList();
        List<ErrorField> errorFields = e.getFieldErrors().stream()
                .map(error -> new ErrorField(error.getField(), messageSource.getMessage(error, Locale.getDefault())))
                .toList();
        return ErrorResponse.builder()
                .message("Validation Error")
                .errors(errors)
                .fields(errorFields)
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse entityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidParameterException(InvalidParameterException e) {
        log.error(e.getMessage());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidTokenException(InvalidTokenException e) {
        log.error(e.getMessage());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse internalServerError(Exception e) {
        log.error(e.getMessage());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
