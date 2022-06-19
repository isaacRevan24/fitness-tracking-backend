package com.service.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Severiano Atencio
 */
@Builder
@ToString
public class AddWeightTrackReqDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7787999439636970403L;

    @NotNull
    private UUID clientId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public AddWeightTrackReqDTO(UUID clientId, Date date) {
        this.clientId = clientId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
