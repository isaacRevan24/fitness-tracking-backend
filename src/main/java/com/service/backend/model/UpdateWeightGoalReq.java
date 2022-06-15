package com.service.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Severiano Atencio
 */
@Builder
@ToString
public class UpdateWeightGoalReq implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 884494327918996513L;

    @NotNull
    private UUID clientId;

    @NotNull
    private Double weightGoal;

    public UpdateWeightGoalReq(UUID clientId, Double weightGoal) {
        this.clientId = clientId;
        this.weightGoal = weightGoal;
    }

    public UpdateWeightGoalReq() {
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public Double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }
}
