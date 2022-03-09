package com.service.backend.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Severiano Atencio
 */
@AllArgsConstructor
public enum StatusEnum {

    SUCCESS("FIT-00", "Success", HttpStatus.OK),
    DATABASE_ERROR("FIT-01", "Database error.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_ERROR("FIT-02", "Internal error.", HttpStatus.INTERNAL_SERVER_ERROR);

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
