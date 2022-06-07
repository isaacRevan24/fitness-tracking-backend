package com.service.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Severiano Atencio
 */
@Builder
@ToString
public class AddGoalsResDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 7691747490264485316L;

    @NotNull
    private Double weightGoal;

    @NotNull
    private Integer stepsGoal;

    public AddGoalsResDTO(Double weightGoal, Integer stepsGoal) {
        this.weightGoal = weightGoal;
        this.stepsGoal = stepsGoal;
    }

    public AddGoalsResDTO() {
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
}
