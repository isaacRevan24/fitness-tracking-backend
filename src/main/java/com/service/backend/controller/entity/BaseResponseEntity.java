package com.service.backend.controller.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.service.backend.controller.dto.StatusDTO;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"status"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8788283495143039830L;

    protected StatusDTO status;

    public BaseResponseEntity(StatusDTO status) {
        this.status = status;
    }

    public BaseResponseEntity() {
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
