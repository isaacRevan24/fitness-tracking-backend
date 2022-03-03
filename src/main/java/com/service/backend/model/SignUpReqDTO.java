package com.service.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@Getter
@Setter
@ToString
public class SignUpReqDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 6254622590391243123L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Size(min = 2, max = 2)
    private String weightUnit;

    @NotNull
    private Double weight;

    @Size(min = 2, max = 2)
    private String heightUnit;

    @NotNull
    private Double height;

    private Integer age;

}
