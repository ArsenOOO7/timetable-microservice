package com.arsen.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * ErrorDTO
 * @author Arsen Sydoryk
 */
@AllArgsConstructor
@Setter @Getter
public class ErrorDto {

    /**
     * Error message
     */
    private String message;

}
