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
public class UpdateStepsGoalReqDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 273107109979090281L;

    @NotNull
    private UUID clientId;

    @NotNull
    private Integer stepsGoal;

    public UpdateStepsGoalReqDTO(UUID clientId, Integer stepsGoal) {
        this.clientId = clientId;
        this.stepsGoal = stepsGoal;
    }

    public UpdateStepsGoalReqDTO() {
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public Integer getStepsGoal() {
        return stepsGoal;
    }

    public void setStepsGoal(Integer stepsGoal) {
        this.stepsGoal = stepsGoal;
    }
}
