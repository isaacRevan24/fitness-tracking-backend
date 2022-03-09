package com.service.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
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

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = -6961415792593648680L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Integer age;

}
