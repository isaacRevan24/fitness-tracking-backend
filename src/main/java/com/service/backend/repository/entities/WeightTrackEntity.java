package com.service.backend.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "weight_track")
@Table(name = "weight_track")
public class WeightTrackEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7326810366049032079L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "weight_track_id")
    private UUID weightTrackId;

    @Column(name = "id")
    private UUID id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
