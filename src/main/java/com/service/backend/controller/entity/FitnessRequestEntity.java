package com.service.backend.controller.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"body"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FitnessRequestEntity<T> {

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
