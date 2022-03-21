package com.service.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@Builder
@ToString
public class SignInResDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = -7618888453420750819L;

    private String userId;

    public SignInResDTO() {
    }

    public SignInResDTO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
