package com.service.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@Builder
@ToString
public class SignInReqDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 8406511902689685785L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
