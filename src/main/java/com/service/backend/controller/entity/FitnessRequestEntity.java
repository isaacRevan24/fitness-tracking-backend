package com.service.backend.controller.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@JsonPropertyOrder({"body"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FitnessRequestEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5749472084026228662L;

    @Valid
    protected transient T body;

    public FitnessRequestEntity(T body) {
        this.body = body;
    }

    public FitnessRequestEntity() {
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FitnessRequestEntity{" +
                "body=" + body +
                '}';
    }
}
