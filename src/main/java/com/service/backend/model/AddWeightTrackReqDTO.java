package com.service.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Severiano Atencio
 */
@Builder
@ToString
public class AddWeightTrackReqDTO implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = -7787999439636970403L;

    @NotNull
    private UUID clientId;

    @NotNull
    private Double weight;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public AddWeightTrackReqDTO(UUID clientId, Double weight, LocalDateTime date) {
        this.clientId = clientId;
        this.weight = weight;
        this.date = date;
    }

    public AddWeightTrackReqDTO() {
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
