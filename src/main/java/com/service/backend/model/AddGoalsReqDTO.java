package com.service.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@Builder
@ToString
public class AddGoalsReqDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = -7951353229816399975L;

    @NotBlank
    private String clientId;

    @NotNull
    private Double weightGoal;

    @NotNull
    private Integer stepsGoal;

    public AddGoalsReqDTO() {
    }

    public AddGoalsReqDTO(String clientId, Double weightGoal, Integer stepsGoal) {
        this.clientId = clientId;
        this.weightGoal = weightGoal;
        this.stepsGoal = stepsGoal;
    }

    public Double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public Integer getStepsGoal() {
        return stepsGoal;
    }

    public void setStepsGoal(Integer stepsGoal) {
        this.stepsGoal = stepsGoal;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
