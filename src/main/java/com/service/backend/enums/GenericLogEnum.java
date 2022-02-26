package com.service.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Severiano Atencio
 */
@Getter
@AllArgsConstructor
public enum GenericLogEnum {

    START_MESSAGE("Start of "),
    FINISH_MESSAGE("End of ");

    private final String message;
}
