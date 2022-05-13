package com.service.backend.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Severiano Atencio
 */
@AllArgsConstructor
public enum StatusEnum {

    SUCCESS("FIT-00", "Success.", HttpStatus.OK),
    DATABASE_ERROR("FIT-01", "Database error.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_ERROR("FIT-02", "Internal error.", HttpStatus.INTERNAL_SERVER_ERROR),
    ENCRYPTION_EXCEPTION("FIT-03", "Encryption error.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_PASSWORD_FORMAT("FIT-04", "Invalid password format.", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_GETTING_PASSWORD("FIT-05", "Error getting the password.", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_VALIDATION_PASSWORD("FIT-06", "Error validating password.", HttpStatus.INTERNAL_SERVER_ERROR),
    SIGN_IN_ERROR("FIT-07", "Cant sign in.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
