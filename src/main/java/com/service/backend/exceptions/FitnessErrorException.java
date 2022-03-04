package com.service.backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * @author Severiano Atencio
 */
@Getter
public class FitnessErrorException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5214871442216349181L;

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpError;

    public FitnessErrorException(Throwable cause, String errorCode, String errorMessage, HttpStatus httpError) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpError = httpError;
    }

    public FitnessErrorException(String message, Throwable cause, String errorCode, String errorMessage, HttpStatus httpError) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpError = httpError;
    }

    public FitnessErrorException() {
        this.errorCode = null;
        this.errorMessage = null;
        this.httpError = null;
    }

}
