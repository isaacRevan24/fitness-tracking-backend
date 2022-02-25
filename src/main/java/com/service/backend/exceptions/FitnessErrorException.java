package com.service.backend.exceptions;

import lombok.Getter;

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

    public FitnessErrorException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public FitnessErrorException() {
        this.errorCode = null;
        this.errorMessage = null;
    }
}
