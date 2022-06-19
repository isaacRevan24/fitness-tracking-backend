package com.service.backend.model;

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
public class AddWeightTrackResDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1710497286422278935L;

    @NotNull
    private UUID weightTrackId;

    public AddWeightTrackResDTO(UUID weightTrackId) {
        this.weightTrackId = weightTrackId;
    }

    public AddWeightTrackResDTO() {
    }

    public UUID getWeightTrackId() {
        return weightTrackId;
    }

    public void setWeightTrackId(UUID weightTrackId) {
        this.weightTrackId = weightTrackId;
    }
}
