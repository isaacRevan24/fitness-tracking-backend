package com.service.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@Getter
@Setter
@ToString
@Builder
public class SignUpReqDTO implements Serializable {

    @JsonIgnore
    @Serial
    private static final long serialVersionUID = 39065090501393208L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Integer age;

    @NotBlank
    @Length(min = 0, max = 2)
    private String weightMetric;

    @NotNull
    private Double weight;

    @NotBlank
    @Length(min = 0, max = 2)
    private String heightMetric;

    @NotNull
    private Double height;

}
