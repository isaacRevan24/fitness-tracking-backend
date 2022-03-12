package com.service.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Severiano Atencio
 */
public enum GenericLogEnum {

    START_MESSAGE("Start of "),
    FINISH_MESSAGE("End of ");

    private final String message;

    GenericLogEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
