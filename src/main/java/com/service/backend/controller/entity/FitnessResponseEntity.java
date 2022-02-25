package com.service.backend.controller.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;

/**
 * @author Severiano Atencio
 */
@JsonPropertyOrder({"status", "body"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FitnessResponseEntity<T> extends BaseResponseEntity {

    @Serial
    private static final long serialVersionUID = 8331872137850836928L;

    protected transient T body;

    public FitnessResponseEntity(T body) {
        this.body = body;
    }

    public FitnessResponseEntity() {
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FitnessResponseEntity{" +
                "body=" + body +
                '}';
    }
}
