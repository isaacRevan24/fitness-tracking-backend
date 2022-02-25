package com.service.backend.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@Getter
@Setter
public class SignUpReqDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6254622590391243123L;

    private String userName;

    private String password;

}
