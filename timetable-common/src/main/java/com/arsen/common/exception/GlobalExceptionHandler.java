package com.arsen.common.exception;

import com.arsen.common.dto.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(TimetableException.class)
    public ResponseEntity<ErrorDto> baseException(TimetableException exception){
        return getErrorDto(exception, exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> baseException(Exception exception){
        return getErrorDto(exception, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDto> getErrorDto(Exception exception, HttpStatus status){
        log.error(exception.getMessage());
        return ResponseEntity.status(status).body(new ErrorDto(exception.getMessage()));
    }

}
