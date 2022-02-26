package com.service.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Severiano Atencio
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    SUCCESS("FIT-00", "Success", HttpStatus.OK);

    private final String code;
    private final String message;
    private final HttpStatus status;

}
